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

        setViewFields(view, plant);

        return new AlertDialog.Builder(requireContext())
                .setTitle(plant.getPlantType().getName())
                .setView(view)
                .setPositiveButton(R.string.close, null)
                .create();
    }

    // Set plant fields to the dialog view
    private void setViewFields(View view, PlantModel plant) {
        TextView growthPhaseName = view.findViewById(R.id.growthPhaseName);
        TextView plantingDate = view.findViewById(R.id.plantingDate);
        TextView growthPhaseDetails = view.findViewById(R.id.growthPhaseDetails);
        TextView soilMoisture = view.findViewById(R.id.soilMoisture);
        TextView minSoilMoisture = view.findViewById(R.id.minSoilMoisture);
        TextView maxSoilMoisture = view.findViewById(R.id.maxSoilMoisture);
        TextView temperature = view.findViewById(R.id.temperature);
        TextView humidity = view.findViewById(R.id.humidity);
        TextView light = view.findViewById(R.id.light);
        TextView warnings = view.findViewById(R.id.warnings);

        growthPhaseName.setText(plant.getGrowthPhaseName());
        plantingDate.setText(plant.getPlantingDate());
        growthPhaseDetails.setText(plant.getGrowthPhaseDetails());
        soilMoisture.setText(String.valueOf(plant.getSoilMoisture()));
        minSoilMoisture.setText(String.valueOf(plant.getMinSoilMoisture()));
        maxSoilMoisture.setText(String.valueOf(plant.getMaxSoilMoisture()));
        temperature.setText(String.valueOf((plant.getTemperature())));
        humidity.setText(String.valueOf((plant.getHumidity())));
        light.setText(String.valueOf((plant.getLight())));
        warnings.setText(android.text.TextUtils.join(", ", plant.getWarnings()));
    }
}

