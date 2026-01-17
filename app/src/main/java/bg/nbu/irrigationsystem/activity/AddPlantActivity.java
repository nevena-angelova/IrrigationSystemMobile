package bg.nbu.irrigationsystem.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import bg.nbu.irrigationsystem.ApiClient;
import bg.nbu.irrigationsystem.ApiService;
import bg.nbu.irrigationsystem.R;
import bg.nbu.irrigationsystem.SessionManager;
import bg.nbu.irrigationsystem.adapter.PlantTypeAdapter;
import bg.nbu.irrigationsystem.model.PlantTypeModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddPlantActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_plant);

        SessionManager session = new SessionManager(this);
        if (!session.isLoggedIn()) {
            Intent intent = new Intent(AddPlantActivity.this, LoginActivity.class);
            intent.setAction(Intent.ACTION_VIEW);
            startActivity(intent);
        }

        ApiService apiService = ApiClient.getClient(this).create(ApiService.class);
        apiService.getPlantTypes().enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<PlantTypeModel>> call, Response<List<PlantTypeModel>> response) {
                if (response.isSuccessful() && response.body() != null) {

                    List<PlantTypeModel> plantTypes = response.body();

                    PlantTypeAdapter adapter = new PlantTypeAdapter(AddPlantActivity.this, plantTypes);

                    Spinner spinner = findViewById(R.id.plantTypes);
                    spinner.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<PlantTypeModel>> call, Throwable t) {
                Toast.makeText(AddPlantActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}