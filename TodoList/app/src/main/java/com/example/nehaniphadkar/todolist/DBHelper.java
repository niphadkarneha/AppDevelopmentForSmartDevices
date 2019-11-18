package com.example.nehaniphadkar.todolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by neha on 1/31/2018.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static String tablename = "TaskDetailsNew";
    public static String columnname = "title";


    public DBHelper(Context context) {
        super(context, "DataBaseFinal.db", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String tableEmp = "create table TaskDetailsNew(title text,description  text)";

/*
        String tableEmp =("create table "+tablename + "("+idcolumn+"integer primary key autoincrement,"+titlecolumn+" text ,"+descriptioncolumn+"text "+")");
*/
        db.execSQL(tableEmp);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { //used to drop exising table
/*
        db.execSQL("drop table "+tablename+" if exists",null);
*/

    }

    public boolean insertData(Task task) {
        try {


            SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("title", task.getTitle());
            values.put("description", task.getDescription());
            long rows = sqLiteDatabase.insert("TaskDetailsNew", null, values);
            sqLiteDatabase.close();
            return rows > 0;
        } catch (Exception e) {
            return false;

        }

    }

    public List<Task> showList() {
        try {
            List<Task> tasks = new ArrayList<>();
            SQLiteDatabase sqLiteDatabase = getWritableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery("select * from " + tablename, null);
            if (cursor.moveToFirst()) {
                do {
                    Task task = new Task();
                    task.setTitle(cursor.getString(0));
                    task.setDescription(cursor.getString(1));
                    tasks.add(task);
                } while (cursor.moveToNext());
            }
            sqLiteDatabase.close();
            return tasks;
        } catch (Exception e) {
            return null;

        }

    }

    public  boolean deleteTask(String task)
    {
        try {
            SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
/*
            String t=task.getTitle();
*/
/*
           long r = sqLiteDatabase.delete(tablename, "title" +  " = " + task ,null);
*/

          long r=  sqLiteDatabase.delete(tablename, "title" + " = ?",
                    new String[] { String.valueOf(task) });

           sqLiteDatabase.close();
           return r > 0;
        }
        catch (SQLException e)
        {
            Log.v("exc",e.toString());
            return false;

        }
    }


}
