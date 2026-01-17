package bg.nbu.irrigationsystem.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import bg.nbu.irrigationsystem.R;
import bg.nbu.irrigationsystem.model.PlantModel;
import bg.nbu.irrigationsystem.model.ReportModel;

public class PlantAdapter extends ArrayAdapter<PlantModel> {

    public PlantAdapter(Context context, List<PlantModel> plants) {
        super(context, 0, plants);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext())
                    .inflate(R.layout.plant_list_item, parent, false);
        }

        PlantModel currentPlant = getItem(position);
        if (currentPlant == null) {
            return listItemView;
        }

        TextView plantIconView = listItemView.findViewById(R.id.plantIcon);
        plantIconView.setText(currentPlant.getIcon());

        TextView plantTypeView = listItemView.findViewById(R.id.plantType);
        if (currentPlant.getPlantType() != null) {
            plantTypeView.setText(currentPlant.getPlantType().getName());
        } else {
            plantTypeView.setText("N/A");
        }

        ReportModel report = currentPlant.getReport();
        if (report != null) {

            // Growth phase name
            TextView growthPhaseNameView = listItemView.findViewById(R.id.growthPhaseName);
            growthPhaseNameView.setText(report.getGrowthPhaseName());

            // Soil moisture
            TextView soilMoistureView = listItemView.findViewById(R.id.soilMoisture);
            soilMoistureView.setText(String.valueOf(report.getSoilMoisture()));

            // Warnings (join list to string)
            TextView warningsView = listItemView.findViewById(R.id.warnings);
            if (warningsView != null) {
                List<String> warnings = report.getWarnings();
                if (warnings != null && !warnings.isEmpty()) {
                    warningsView.setText(android.text.TextUtils.join(", ", warnings));
                } else {
                    warningsView.setText("No warnings");
                }
            }

        }

        return listItemView;
    }
}
