package com.example.nehaniphadkar.todolist;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


@SuppressWarnings("ALL")
public class TaskAdapter extends ArrayAdapter<Task> {
private MainActivity context;
private List<Task> tasks;

    public TaskAdapter(@NonNull MainActivity context, List<Task> tasks) {
        super(context, R.layout.task_item,tasks);
        this.context=context;
        this.tasks=tasks;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.task_item,parent,false);
view.setOnLongClickListener(new View.OnLongClickListener() {
    @Override
    public boolean onLongClick(View v) {
        String title = tasks.get(position).getTitle();
        DBHelper helper = new DBHelper(context);
        if (helper.deleteTask(title)) {
            System.out.println("dwiasi");
            Toast.makeText(context, " Task deleted successfully", Toast.LENGTH_LONG).show();
            context.display();

        } else {
            Toast.makeText(context, "Not deleted", Toast.LENGTH_LONG).show();
        }
        return true;
    }
});
        CheckBox check=(CheckBox)view.findViewById(R.id.checkbox) ;
        check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                String title = tasks.get(position).getTitle();
                DBHelper helper = new DBHelper(context);
                if (helper.deleteTask(title)) {

                    Toast.makeText(context, " Task deleted successfully", Toast.LENGTH_LONG).show();
                    context.display();

                } else {
                    Toast.makeText(context, "Not deleted", Toast.LENGTH_LONG).show();
                }
            }
        });
        TextView tvtitle=(TextView)view.findViewById(R.id.tv_title);
        TextView tvdescription=(TextView)view.findViewById(R.id.tv_desc);
        tvtitle.setText(tasks.get(position).getTitle());
        tvdescription.setText(tasks.get(position).getDescription());
        return view;

    }
}






