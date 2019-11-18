package com.example.nehaniphadkar.goldenpalace;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by neha on 3/14/2018.
 */

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    private List<SaladModel> android_versions;
    private Context context;

    public DataAdapter(Context context,List<SaladModel> android_versions) {
        this.context = context;
        this.android_versions = android_versions;

    }

    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_layout, viewGroup, false);
        return new ViewHolder(view,context,android_versions);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {


        viewHolder.img_android.setImageResource(android_versions.get(i).imageId);
        viewHolder.tv_android.setText(android_versions.get(i).txt);

/*
        viewHolder.tv_android.setText(android_versions.get(i).);
*/
/*
        Picasso.with(context).load(android_versions.get(i).getAndroid_image_url()).resize(120, 60).into(viewHolder.img_android);
*/
    }

    @Override
    public int getItemCount() {
        return android_versions.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final List<SaladModel> list;
        private final Context context;
        TextView tv_android;
        ImageView img_android;
        public ViewHolder(View view, Context context, List<SaladModel> android_versions) {
            super(view);
            this.context=context;
            this.list=android_versions;

            tv_android = (TextView)view.findViewById(R.id.tv_android);
            img_android = (ImageView)view.findViewById(R.id.img_android);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent intent=new Intent(context,SaladItemsActivity.class);
            intent.putExtra("id",list.get(getAdapterPosition()).getId());

            context.startActivity(intent);

        }
    }
}
