package bg.nbu.irrigationsystem;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;

import bg.nbu.irrigationsystem.model.PlantModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlantDialogFragment extends DialogFragment {

    private static final String ARG_PLANT = "plant";

    Button btn_irrigate;


    public static PlantDialogFragment newInstance(PlantModel plant) {
        PlantDialogFragment fragment = new PlantDialogFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PLANT, plant);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        PlantModel plant = (PlantModel) getArguments().getSerializable(ARG_PLANT);

        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_fragment_plant, null);

        TextView growthPhaseName = view.findViewById(R.id.growthPhaseName);
        TextView plantingDate = view.findViewById(R.id.plantingDate);
        TextView growthPhaseDetails = view.findViewById(R.id.growthPhaseDetails);
        TextView soilMoisture = view.findViewById(R.id.soilMoisture);
        TextView minSoilMoisture = view.findViewById(R.id.minSoilMoisture);
        TextView maxSoilMoisture = view.findViewById(R.id.maxSoilMoisture);
        TextView irrigationDuration = view.findViewById(R.id.irrigationDuration);
        TextView warnings = view.findViewById(R.id.warnings);

        growthPhaseName.setText(plant.getGrowthPhaseName());
        plantingDate.setText(plant.getPlantingDate());
        growthPhaseDetails.setText(plant.getGrowthPhaseDetails());
        soilMoisture.setText(String.valueOf(plant.getSoilMoisture()));
        minSoilMoisture.setText(String.valueOf(plant.getMinSoilMoisture()));
        maxSoilMoisture.setText(String.valueOf(plant.getMaxSoilMoisture()));
        irrigationDuration.setText(String.valueOf(plant.getIrrigationDuration()));
        warnings.setText(android.text.TextUtils.join(", ", plant.getWarnings()));

        btn_irrigate = view.findViewById(R.id.btnIrrigate);

        if(plant.needsIrrigation()){
            btn_irrigate.setVisibility(View.VISIBLE);
            btn_irrigate.setOnClickListener(v -> {
                irrigate(plant.getDeviceId(), plant.getRelayId(), plant.getIrrigationDuration());
            });
        }
        else {
            btn_irrigate.setVisibility(View.GONE);
        }

        return new AlertDialog.Builder(requireContext())
                .setTitle(plant.getPlantType().getName())
                .setView(view)
                .setPositiveButton("OK", null)
                .create();
    }

    private void irrigate(int deviceId, int relayId, double irrigationDuration) {

        ApiService apiService = ApiClient.getClient(getContext()).create(ApiService.class);
        apiService.irrigate(deviceId, relayId, (int) irrigationDuration).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Toast.makeText(getContext(), response.body() , Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(getContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }

        });
    }
}

