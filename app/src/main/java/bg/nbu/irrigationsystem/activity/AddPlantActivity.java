package bg.nbu.irrigationsystem.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.JsonObject;

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

    Spinner spinner;
    EditText et_planting_date;

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

        spinner = findViewById(R.id.plantTypes);

        ApiService apiService = ApiClient.getClient(this).create(ApiService.class);
        apiService.getPlantTypes().enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<PlantTypeModel>> call, Response<List<PlantTypeModel>> response) {
                if (response.isSuccessful() && response.body() != null) {

                    List<PlantTypeModel> plantTypes = response.body();

                    PlantTypeAdapter adapter = new PlantTypeAdapter(AddPlantActivity.this, plantTypes);

                    spinner.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<PlantTypeModel>> call, Throwable t) {
                Toast.makeText(AddPlantActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        Button addButton = findViewById(R.id.login);

        addButton.setOnClickListener(view -> {

            PlantTypeModel selectedPlant = (PlantTypeModel) spinner.getSelectedItem();
            Integer plantTypeId = selectedPlant.getId();

            et_planting_date= findViewById(R.id.plantingDate);
            String plantingDate = et_planting_date.getText().toString();

            JsonObject plantInfo = new JsonObject();
            plantInfo.addProperty("plantTypeId", plantTypeId);
            plantInfo.addProperty("plantingDate", plantingDate);

            apiService.createPlant(plantInfo).enqueue(new Callback<String>() {

                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(AddPlantActivity.this, response.body() , Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Toast.makeText(AddPlantActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });

    }
}