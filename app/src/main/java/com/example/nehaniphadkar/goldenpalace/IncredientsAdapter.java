package com.example.nehaniphadkar.goldenpalace;

import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by neha on 15-Mar-18.
 */

public class IncredientsAdapter extends RecyclerView.Adapter<IncredientsAdapter.InfoHolder> {
    private final SaladItemsActivity context;
    private final ArrayList<String> list;
    private final boolean b;

    public IncredientsAdapter(SaladItemsActivity saladItemsActivity, ArrayList<String> data, boolean b) {
        this.context = saladItemsActivity;
        this.list = data;
        this.b=b;
    }

    @Override
    public IncredientsAdapter.InfoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(b?R.layout.incredient_adapter:R.layout.saladadapter, parent, false);

        return new InfoHolder(view);
    }

    @Override
    public void onBindViewHolder(final IncredientsAdapter.InfoHolder holder, int position) {
        if (b){
            holder.check.setVisibility(View.VISIBLE);
            holder.text.setText(list.get(position));

        }
else{
            holder.check.setVisibility(View.GONE);

            holder.text.setText(position+". "+list.get(position));

        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class InfoHolder extends RecyclerView.ViewHolder {
        private final TextView text;
        private final AppCompatCheckBox check;

        public InfoHolder(View itemView) {
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.incredientsText);
            check = (AppCompatCheckBox) itemView.findViewById(R.id.check);
        }
    }
}
