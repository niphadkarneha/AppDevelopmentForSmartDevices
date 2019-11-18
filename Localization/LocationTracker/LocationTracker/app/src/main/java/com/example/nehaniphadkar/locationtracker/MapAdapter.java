package com.example.nehaniphadkar.locationtracker;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by neha on 07-Apr-18.
 */

public class MapAdapter extends RecyclerView.Adapter<MapAdapter.InfoHolder> {
    private final CheckedInLocations context;
    private final List<MapClass> list;

    public MapAdapter(CheckedInLocations checkedInLocations, List<MapClass> mapClasses) {
        this.context=checkedInLocations;
        this.list=mapClasses;

    }

    @Override
    public MapAdapter.InfoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.map_adapter,parent,false);

        return new InfoHolder(view);
    }

    @Override
    public void onBindViewHolder(MapAdapter.InfoHolder holder, int position) {
holder.name.setText(list.get(position).getName());
holder.address.setText(list.get(position).getAddress());
holder.date.setText(list.get(position).getTime());
holder.lat.setText("Lattitude is : "+list.get(position).getLat());
        holder.longitude.setText("Longitude is : "+list.get(position).getLongi());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class InfoHolder extends RecyclerView.ViewHolder {
        private final TextView name;
        private final TextView address;
        private final TextView lat;
        private final TextView longitude;
        private final TextView date;


        public InfoHolder(View itemView) {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.name);
            address=(TextView)itemView.findViewById(R.id.address);
            lat=(TextView)itemView.findViewById(R.id.lat);
            longitude=(TextView)itemView.findViewById(R.id.longitude);
            date=(TextView)itemView.findViewById(R.id.date);

        }
    }
}
