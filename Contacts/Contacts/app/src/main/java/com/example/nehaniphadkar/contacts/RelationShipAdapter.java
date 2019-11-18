package com.example.nehaniphadkar.contacts;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by neha on 02-Mar-18.
 */

public class RelationShipAdapter extends ArrayAdapter<Contact> {
    private Context context;
    private List<Contact> tasks;
    private MyAdapter.ChangeFilterMainNumber listerner;


    public RelationShipAdapter(@NonNull Context context, List<Contact> tasks) {
        super(context, R.layout.task_item,tasks);
        this.context=context;
        this.tasks=tasks;

    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.task_item,parent,false);
        TextView tvtitle=(TextView)view.findViewById(R.id.tv_title);
    /*    selectedStrings=new ArrayList<>();
        numberstring=new ArrayList<>();*/
/*
        TextView tvdescription=(TextView)view.findViewById(R.id.tv_desc);
*/
        final CheckBox checkBox=(CheckBox)view.findViewById(R.id.ch_delete);
        checkBox.setVisibility(View.GONE);


        tvtitle.setText(tasks.get(position).getName());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper helper = new DBHelper(context);
                Contact contact=helper.getContact(tasks.get(position).getName());
                String name=contact.getName();
                String number= contact.getNumber();
                String json= contact.getJson();
                byte[] bytes=contact.getBlob();
                System.out.println("sdxaszx"+name+number+json);
                listerner = (MyAdapter.ChangeFilterMainNumber) context;

                listerner.OnChangeFilterMainNumberListener(name,number,json,bytes);



            }
        });
/*
        tvdescription.setText(tasks.get(position).getDescription());
*/
        return view;

    }


}