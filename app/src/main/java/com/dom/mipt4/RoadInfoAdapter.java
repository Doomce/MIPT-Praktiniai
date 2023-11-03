package com.dom.mipt4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class RoadInfoAdapter extends BaseAdapter {

    private List<RoadInfo> roadList;
    private LayoutInflater inflater;

    public RoadInfoAdapter(Context applicationContext, List<RoadInfo> roadList) {
        this.roadList = roadList;
        inflater = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return roadList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.road, null);
        RoadInfo road = roadList.get(position);

        TextView title = convertView.findViewById(R.id.roadTitle);
        TextView no = convertView.findViewById(R.id.roadNo);
        TextView name = convertView.findViewById(R.id.roadName);
        TextView temp = convertView.findViewById(R.id.temperature);
        TextView date = convertView.findViewById(R.id.date);
        TextView condition = convertView.findViewById(R.id.roadCondition);

        title.setText(String.format("%s (%s km)", road.name, road.positionAt));
        no.setText(String.format("%s", road.roadNumber));
        name.setText(String.format("%s", road.roadName));
        temp.setText(String.format("Temperatura: %s°C", road.temperature));
        date.setText(String.format("Užfiksuota: %s", road.capturedAt));
        condition.setText(String.format("Kelio dangos būklė: %s", road.roadCondition));

        return convertView;
    }
}
