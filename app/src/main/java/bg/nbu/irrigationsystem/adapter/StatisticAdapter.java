package bg.nbu.irrigationsystem.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import bg.nbu.irrigationsystem.R;
import bg.nbu.irrigationsystem.model.EtcStatisticModel;

public class StatisticAdapter extends ArrayAdapter<EtcStatisticModel> {


    public StatisticAdapter(Context context, List<EtcStatisticModel> statistics) {
        super(context, 0, statistics);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext())
                    .inflate(R.layout.statistic_list_item, parent, false);
        }

        EtcStatisticModel currentStatistic = getItem(position);

        TextView controllerIdView = listItemView.findViewById(R.id.controllerId);
        controllerIdView.setText(String.valueOf(currentStatistic.getControllerId()));

        TextView plantTypeView = listItemView.findViewById(R.id.plantType);
        plantTypeView.setText(String.valueOf(currentStatistic.getPlantType()));

        TextView dateView = listItemView.findViewById(R.id.date);
        dateView.setText(currentStatistic.getDate());

        TextView tMinView = listItemView.findViewById(R.id.tMin);
        tMinView.setText(String.valueOf(currentStatistic.gettMin()));

        TextView tMaxView = listItemView.findViewById(R.id.tMax);
        tMaxView.setText(String.valueOf(currentStatistic.gettMax()));

        TextView tMeanView = listItemView.findViewById(R.id.tMean);
        tMeanView.setText(String.valueOf(currentStatistic.gettMean()));

        TextView rhMinView = listItemView.findViewById(R.id.rhMin);
        rhMinView.setText(String.valueOf(currentStatistic.getRhMin()));

        TextView rhMaxView = listItemView.findViewById(R.id.rhMax);
        rhMaxView.setText(String.valueOf(currentStatistic.getRhMax()));

        TextView etcView = listItemView.findViewById(R.id.etc);
        etcView.setText(String.valueOf(currentStatistic.getEtc()));

        return listItemView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getView(position, convertView, parent);
    }
}
