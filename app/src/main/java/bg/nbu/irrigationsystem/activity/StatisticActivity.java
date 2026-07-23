package bg.nbu.irrigationsystem.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import bg.nbu.irrigationsystem.ApiClient;
import bg.nbu.irrigationsystem.ApiService;
import bg.nbu.irrigationsystem.PlantDialogFragment;
import bg.nbu.irrigationsystem.R;
import bg.nbu.irrigationsystem.SessionManager;
import bg.nbu.irrigationsystem.adapter.PlantAdapter;
import bg.nbu.irrigationsystem.adapter.StatisticAdapter;
import bg.nbu.irrigationsystem.model.EtcStatisticModel;
import bg.nbu.irrigationsystem.model.PlantModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StatisticActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);

        // Check if user is logged in and redirect to Login page if not
        SessionManager session = new SessionManager(this);
        if (!session.isLoggedIn()) {
            Intent intent = new Intent(StatisticActivity.this, LoginActivity.class);
            intent.setAction(Intent.ACTION_VIEW);
            startActivity(intent);
        }

        getEtcStatistic();
    }

    private void getEtcStatistic(){
        ApiService apiService = ApiClient.getClient(this).create(ApiService.class);
        apiService.getEtcStatistics().enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<EtcStatisticModel>> call, Response<List<EtcStatisticModel>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<EtcStatisticModel> plants = response.body();
                    setListView(plants);
                }
            }

            @Override
            public void onFailure(Call<List<EtcStatisticModel>> call, Throwable t) {
                Toast.makeText(StatisticActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setListView(List<EtcStatisticModel> statistics){
        StatisticAdapter adapter = new StatisticAdapter(StatisticActivity.this, statistics);
        ListView listView = findViewById(R.id.statistic);
        listView.setAdapter(adapter);
    }
}