package com.example.nehaniphadkar.todolist;

import android.animation.TimeAnimator;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.CALL_PHONE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class MainActivity extends AppCompatActivity {

    private EditText edtTitle;
    private EditText edtDesc;
    private Button btnAdd;
    private ListView recTask;
    private TaskAdapter adapter;
    private List<Task> taskList;
    private List<Task> tasks;
    private Button btnLoad;
    private static final String filename = "samplefile.txt";
    private String title;
    private String desc;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(checkPermission()){

            //Toast.makeText(MainActivity.this, "All Permissions Granted Successfully", Toast.LENGTH_LONG).show();

        }
        else {

            requestPermission();
        }
        initUI();
        initListener();
  /*recTask.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {


                int p=recTask.getCheckedItemPosition();


                Task selectedFromList = (Task) recTask.getItemAtPosition(position);
                String title = selectedFromList.getTitle();
                DBHelper helper = new DBHelper(MainActivity.this);
                Log.v("ddddd", selectedFromList.getTitle().toString());
                if (helper.deleteTask(title)) {

                    Toast.makeText(MainActivity.this, " Task deleted successfully", Toast.LENGTH_LONG).show();

                    display();
                } else {
                    Toast.makeText(MainActivity.this, "Not deleted", Toast.LENGTH_LONG).show();
                }

                return false;

            }
        });*/
   /*    recTask.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               if (view.getId()==R.id.checkbox){
                   Toast.makeText(MainActivity.this, "cisjd", Toast.LENGTH_SHORT).show();
               }
           }
       });*/
/*        recTask.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {

                    Task selectedFromList = (Task) recTask.getItemAtPosition(position);
                String title = selectedFromList.getTitle();
                DBHelper helper = new DBHelper(MainActivity.this);
                Log.v("ddddd", selectedFromList.getTitle().toString());
                if (helper.deleteTask(title)) {

                    Toast.makeText(MainActivity.this, " Task deleted successfully", Toast.LENGTH_LONG).show();

                    display();
                } else {
                    Toast.makeText(MainActivity.this, "Not deleted", Toast.LENGTH_LONG).show();
                }



                }
                 return false;

        });*/


    }


    private void initListener() {
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtTitle.getText().toString().matches("")){
                    edtTitle.requestFocus();
                    edtTitle.setError("please enter title");
                }
                else if(edtDesc.getText().toString().matches("")){
                    edtDesc.requestFocus();
                    edtDesc.setError("please desrcibe it");

                }else {
                    addData();
                }


            }


        });
    }


    private void addData() {

         title = edtTitle.getText().toString();
         desc = edtDesc.getText().toString();
        Task task = new Task();
        task.setTitle(title);
        task.setDescription(desc);
        DBHelper database = new DBHelper(MainActivity.this);
        if (database.insertData(task)) {
            Toast.makeText(MainActivity.this, "Task inserted successfully", Toast.LENGTH_LONG).show();
            edtTitle.setText("");
            edtDesc.setText("");
            display();
            saveEx();
        } else {
            Toast.makeText(MainActivity.this, " Task not inserted", Toast.LENGTH_LONG).show();
        }

    }


    public void display() {

        DBHelper database1 = new DBHelper(MainActivity.this);
        recTask.setAdapter(new TaskAdapter(this, database1.showList()));
    }

    private void initUI() {
        edtTitle = (EditText) findViewById(R.id.edt_title);
        edtDesc = (EditText) findViewById(R.id.edt_description);
        btnAdd = (Button) findViewById(R.id.btnid);
        recTask = (ListView) findViewById(R.id.card_recycler_view);
        recTask.setItemsCanFocus(true);
    }
    private void saveEx() {
        String state;
        state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            File root = Environment.getExternalStorageDirectory();
            File dir = new File(root.getAbsolutePath() + "/MyAppFile");
            if (!dir.exists()) {
                dir.mkdir();
            }
            File file = new File(dir, "test.txt");
            try {
                FileOutputStream fileOutputStream =new FileOutputStream(file,true);
                String s = title+ " " + " "+ desc + " , ";
                fileOutputStream.write(s.getBytes());
                fileOutputStream.close();
               // Toast.makeText(MainActivity.this, "file created", Toast.LENGTH_LONG).show();
            } catch (FileNotFoundException e) {


            } catch (IOException e) {


            }


        } else {
            Toast.makeText(MainActivity.this, "Sd card not there", Toast.LENGTH_LONG).show();
        }


    }

    @Override
    protected void onResume() {
        super.onResume();
        displyOnresume();

    }

    private void displyOnresume() {

        DBHelper database1 = new DBHelper(MainActivity.this);
        tasks = database1.showList();
        if (!tasks.equals(null)) {
            recTask.setAdapter(new TaskAdapter(MainActivity.this, tasks));
        } else {
            recTask.setVisibility(View.INVISIBLE);
        }


    }
    private void requestPermission() {

        ActivityCompat.requestPermissions(MainActivity.this, new String[]
                {
                        WRITE_EXTERNAL_STORAGE
                }, 1);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {

            case 1:

                if (grantResults.length > 0) {

                    boolean CameraPermission = grantResults[0] == PackageManager.PERMISSION_GRANTED;


                    if (CameraPermission ) {


                    }
                    else {

                    }
                }

                break;
        }
    }

    public boolean checkPermission() {

        int FirstPermissionResult = ContextCompat.checkSelfPermission(getApplicationContext(), WRITE_EXTERNAL_STORAGE);

        return FirstPermissionResult == PackageManager.PERMISSION_GRANTED ;

    }
}
