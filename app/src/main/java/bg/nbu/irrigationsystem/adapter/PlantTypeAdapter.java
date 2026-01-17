package bg.nbu.irrigationsystem.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import bg.nbu.irrigationsystem.R;
import bg.nbu.irrigationsystem.model.PlantTypeModel;


public class PlantTypeAdapter extends ArrayAdapter<PlantTypeModel> {

    public PlantTypeAdapter(Context context, List<PlantTypeModel> plantTypes) {
        super(context, 0, plantTypes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if there is an existing list item view (called convertView) that we can reuse,
        // otherwise, if convertView is null, then inflate a new list item layout.
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.plant_type_spinner_item, parent, false);
        }

        // Find the plant type at the given position in the list of plants
        PlantTypeModel currentPlantType = getItem(position);

        TextView nameView = listItemView.findViewById(R.id.name);
        nameView.setText(String.valueOf(currentPlantType.getName()));

        // Return the list item view that is now showing the appropriate data
        return listItemView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getView(position, convertView, parent);
    }
}
