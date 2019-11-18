package com.example.nehaniphadkar.goldenpalace;

import java.util.ArrayList;

/**
 * Created by Afzal on 15-Apr-18.
 */

public class SaladDescription {
    public int imageId;
    public String txt;
    public String id;
    public ArrayList<String> listProcedure;
    public ArrayList<String> itemsRequired;
    private String calories;
    private String time;

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<String> getListProcedure() {
        return listProcedure;
    }

    public void setListProcedure(ArrayList<String> listProcedure) {
        this.listProcedure = listProcedure;
    }

    public ArrayList<String> getItemsRequired() {
        return itemsRequired;
    }

    public void setItemsRequired(ArrayList<String> itemsRequired) {
        this.itemsRequired = itemsRequired;
    }

    public String getCalories() {
        return calories;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
