package com.example.nehaniphadkar.contacts;

/**
 * Created by neha on 02-Mar-18.
 */
import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DetailsFragment extends Fragment {
    public static final String EXTRA_TEXT ="text";
    public EditText edtname;
    public EditText edtnumber;
    private Button btnadd;
    private String name;
    private String number;
    private ListView lv;
    private List<Contact> tasks;
    private View view;
    private String nameSaved;
    private String numberSaved;
    private MainListRefresh listRefresh;
    private List myOrderedList;
    private MyListFragment.OnItemSelectedListener listerner;

    public interface MainListRefresh {

        void onUpdate();
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState==null){

        }
        else{
             nameSaved=savedInstanceState.getString("name","");
             numberSaved=savedInstanceState.getString("number","");
            System.out.println("sdwszx"+nameSaved+numberSaved);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.fragment_details,
                container, false);
        initUI();
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtname.getText().toString().matches("")){
                    edtname.setError("please enter name");

                }
                else if(edtnumber.getText().toString().matches("")){
                    edtnumber.setError("please enter number");
                }else {
                    addData();
                }
            }
        });
        show();
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            String text = bundle.getString(EXTRA_TEXT);
            //setText(text);
        }
        edtname.setText(nameSaved);
        edtnumber.setText(numberSaved);
    }

    public void setText(String text) {
        TextView view = (TextView) getView().findViewById(R.id.detailsText);
        view.setText(text);
    }
    private void addData() {

        name = edtname.getText().toString();
        number = edtnumber.getText().toString();
        Contact task = new Contact();
        task.setName(name);
        task.setNumber(number);
        Bitmap bmp = BitmapFactory.decodeResource(getResources(),R.drawable.gallery);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        task.setBlob(byteArray);
        ArrayList<JSONObject>  jsonObjects=new ArrayList<>();
        for (int i = 0; i < TaskAdapter.selectedStrings.size(); i++) {
            try {
                JSONObject jsonObject=new JSONObject();

                jsonObject.put("name",TaskAdapter.selectedStrings.get(i));
                jsonObject.put("number",TaskAdapter.numberstring.get(i));
                jsonObjects.add(jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        System.out.println("dsewas"+jsonObjects.toString());
        task.setJson(jsonObjects.toString());
        DBHelper database = new DBHelper(getActivity());
        if (database.insertData(task)) {
            Toast.makeText(getActivity(), "Task inserted successfully", Toast.LENGTH_LONG).show();
            edtname.setText("");
            edtnumber.setText("");
                    listRefresh = (DetailsFragment.MainListRefresh) getActivity();
                    listRefresh.onUpdate();
            display();
        } else {
            Toast.makeText(getActivity(), " Task not inserted", Toast.LENGTH_LONG).show();
        }


    }

    private void display() {

        DBHelper database1 = new DBHelper(getActivity());
List<Contact> contacts=database1.showList();
        Collections.reverse(contacts);
        lv.setAdapter(new TaskAdapter(getActivity(),contacts));


    }

    private void initUI() {
        edtname=(EditText)view.findViewById(R.id.edt_title);
        edtnumber=(EditText)view.findViewById(R.id.edt_number);
        edtname.setText(nameSaved);
        edtnumber.setText(numberSaved);
        btnadd=(Button)view.findViewById(R.id.btnaddc);
        lv=(ListView)view.findViewById(R.id.card_recycler_view_contact);


    }



    public void show() {

        DBHelper database1 = new DBHelper(getActivity());
        tasks = database1.showList();
        if (!tasks.equals(null)) {
            lv.setAdapter(new TaskAdapter(getActivity(), tasks));
        } else {
            lv.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("name",edtname.getText().toString());
        outState.putString("number",edtnumber.getText().toString());
       /* listerner = (MyListFragment.OnItemSelectedListener) getActivity();
        listerner.onRssItemSelected(edtname.getText().toString(),edtnumber.getText().toString());*/

    }
}
