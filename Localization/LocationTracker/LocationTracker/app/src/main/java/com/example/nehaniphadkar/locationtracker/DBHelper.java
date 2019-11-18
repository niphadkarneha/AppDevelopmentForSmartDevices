package com.example.nehaniphadkar.locationtracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by neha on 1/31/2018.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static String tablename = "Map";
    private final Context context;
/*
    public static String columnname = "title";
*/


    public DBHelper(Context context) {
        super(context, "ContactsDB.db", null, 2);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String tableEmp = "create table Map(name TEXT,lat TEXT,longi TEXT,time TEXT,address TEXT)";

        db.execSQL(tableEmp);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { //used to drop exising table
/*
        db.execSQL("drop table "+tablename+" if exists",null);
*/

    }

    public boolean insertData(MapClass task) {
        try {


            SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("name", task.getName());
            values.put("lat", task.getLat());
            values.put("longi", task.getLongi());
            values.put("time", task.getTime());
            values.put("address", task.getAddress());

            long rows = sqLiteDatabase.insert("Map", null, values);
            sqLiteDatabase.close();
            return rows > 0;
        } catch (Exception e) {
            Toast.makeText(context, e.toString(), Toast.LENGTH_LONG).show();
            return false;

        }

    }

    public List<MapClass> showList() {
        List<MapClass> tasks = new ArrayList<>();

        try {
            SQLiteDatabase sqLiteDatabase = getWritableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery("select * from " + tablename, null);
            if (cursor.moveToFirst()) {
                do {
                    MapClass task = new MapClass();
                    task.setName(cursor.getString(0));
                    task.setLat(cursor.getString(1));
                    task.setLongi(cursor.getString(2));
                    task.setTime(cursor.getString(3));
                    task.setAddress(cursor.getString(4));

                    tasks.add(task);
                } while (cursor.moveToNext());
            }
            sqLiteDatabase.close();
            return tasks;
        } catch (Exception e) {
            Toast.makeText(context, e.toString(), Toast.LENGTH_LONG).show();

            return tasks;

        }

    }

    public boolean deleteTask(List<String> strings) {
        try {
            SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();


            long r = 0;
            for (int i = 0; i < strings.size(); i++) {

                r = sqLiteDatabase.delete(tablename, "name ='" + strings.get(i) + "'", null);
            }
            sqLiteDatabase.close();
            return r > 0;
        } catch (SQLException e) {
            Log.v("exc", e.toString());
            return false;

        }
    }

    public MapClass getData(String nameComparable, String latcomparable, String longcomparable) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Map WHERE lat =" +
                latcomparable + " AND longi =" + longcomparable, null);
       /* Cursor cursor = db.query(tablename, new String[] { "name",
                        "lat", "longi", "time" }, "name" + "=?",
                new String[] { String.valueOf(nameComparable) +"AND lat=?" }, null, null, null, null);
*/
        if (cursor != null)
            System.out.println("xsdx" + cursor.getCount());
        cursor.moveToFirst();
MapClass mapClass=new MapClass();
mapClass.setTime(cursor.getString(cursor.getColumnIndex("time")));
        mapClass.setLat(cursor.getString(cursor.getColumnIndex("lat")));
        mapClass.setLongi(cursor.getString(cursor.getColumnIndex("longi")));
        mapClass.setName(cursor.getString(cursor.getColumnIndex("name")));
        mapClass.setAddress(cursor.getString(cursor.getColumnIndex("address")));

        return mapClass;
    }

/*    MapClass getContact(String id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(tablename, new String[] { "name",
                        "lat", "longi" }, "name" + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);

        if (cursor != null)
            System.out.println("xsdx"+cursor.getCount());
            cursor.moveToFirst();
MapClass contact=new MapClass();
contact.setName(cursor.getString(0));
        contact.setLat(cursor.getString(1));
        contact.setLongi(cursor.getString(2));


        // return contact
        return contact;
    }*/
public boolean update(String task,MapClass contact) {
    try {


        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", contact.getName());
        values.put("lat",contact.getLat());
        values.put("longi",contact.getLongi());
        values.put("time",contact.getTime());
        values.put("address",contact.getAddress());

        String[] args = new String[]{task};

        long rows = sqLiteDatabase.update(tablename, values, "name =?", args);

        sqLiteDatabase.close();
        return rows > 0;
    } catch (Exception e) {
        return false;

    }


}}