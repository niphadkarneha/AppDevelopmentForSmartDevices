package com.example.nehaniphadkar.contacts;

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

    private static String tablename = "Contacts";
/*
    public static String columnname = "title";
*/


    public DBHelper(Context context) {
        super(context, "ContactsDB.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String tableEmp = "create table Contacts(name TEXT,number TEXT,json TEXT, image BLOB NOT NULL )";

        db.execSQL(tableEmp);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { //used to drop exising table
/*
        db.execSQL("drop table "+tablename+" if exists",null);
*/

    }

    public boolean insertData(Contact task) {
        try {


            SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("name", task.getName());
            values.put("number",task.getNumber());
            values.put("json",task.getJson());
            values.put("image",new byte[12]);

            long rows = sqLiteDatabase.insert("Contacts", null, values);
            sqLiteDatabase.close();
            return rows > 0;
        } catch (Exception e) {
            return false;

        }

    }

    public List<Contact> showList() {
        try {
            List<Contact> tasks = new ArrayList<>();
            SQLiteDatabase sqLiteDatabase = getWritableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery("select * from " + tablename, null);
            if (cursor.moveToFirst()) {
                do {
                    Contact task = new Contact();
                    task.setName(cursor.getString(0));
                    task.setNumber(cursor.getString(1));
                    tasks.add(task);
                } while (cursor.moveToNext());
            }
            sqLiteDatabase.close();
            return tasks;
        } catch (Exception e) {
            return null;

        }

    }
    public  boolean deleteTask(List<String> strings)
    {
        try {
            SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();




            long r = 0;
            for(int i = 0; i < strings.size(); i++)
            {

               r= sqLiteDatabase.delete(tablename,"name ='"+ strings.get(i) +"'",null);
            }
           sqLiteDatabase.close();
           return r > 0;
        }
        catch (SQLException e)
        {
            Log.v("exc",e.toString());
            return false;

        }
    }
    Contact getContact(String id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(tablename, new String[] { "name",
                        "number", "json","image" }, "name" + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);

        if (cursor != null)
            System.out.println("xsdx"+cursor.getCount());
            cursor.moveToFirst();
Contact contact=new Contact();
contact.setName(cursor.getString(0));
        contact.setNumber(cursor.getString(1));
        contact.setJson(cursor.getString(2));
        contact.setBlob(cursor.getBlob(3));


        // return contact
        return contact;
    }
    public boolean update(String task, Contact contact) {
        try {


            SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("name", contact.getName());
            values.put("number",contact.getNumber());
            values.put("json",contact.getJson());
            values.put("image",contact.getBlob());
            String[] args = new String[]{task};

            long rows = sqLiteDatabase.update(tablename, values, "name =?", args);

            sqLiteDatabase.close();
            return rows > 0;
        } catch (Exception e) {
            return false;

        }

    }
}
