package bg.nbu.irrigationsystem.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import bg.nbu.irrigationsystem.ApiService;
import bg.nbu.irrigationsystem.PlantDialogFragment;
import bg.nbu.irrigationsystem.R;
import bg.nbu.irrigationsystem.adapter.PlantAdapter;
import bg.nbu.irrigationsystem.model.PlantModel;
import bg.nbu.irrigationsystem.ApiClient;
import bg.nbu.irrigationsystem.SessionManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    Button addPlant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Check if user is logged in and redirect to login if not
        SessionManager session = new SessionManager(this);
        if (!session.isLoggedIn()) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            intent.setAction(Intent.ACTION_VIEW);
            startActivity(intent);
        }

        addPlant = findViewById(R.id.addPlant);
        addPlant.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AddPlantActivity.class);
            intent.setAction(Intent.ACTION_VIEW);
            startActivity(intent);
        });

        setPlants();
    }

    // Get plants from API and set them to the list view
    private void setPlants(){
        ApiService apiService = ApiClient.getClient(this).create(ApiService.class);
        apiService.getPlants().enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<PlantModel>> call, Response<List<PlantModel>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<PlantModel> plants = response.body();
                    setListView(plants);
                }
            }

            @Override
            public void onFailure(Call<List<PlantModel>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Set plants to the list view
    private void setListView(List<PlantModel> plants){
        for (PlantModel plant : plants) {
            plant.setIcon();
        }

        PlantAdapter adapter = new PlantAdapter(MainActivity.this, plants);

        ListView listView = findViewById(R.id.plants);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            PlantModel plant = plants.get(position);
            PlantDialogFragment dialog = PlantDialogFragment.newInstance(plant);
            dialog.show(getSupportFragmentManager(), "PlantDialog");
        });
    }
}