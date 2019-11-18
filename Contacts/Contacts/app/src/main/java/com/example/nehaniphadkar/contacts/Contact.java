package com.example.nehaniphadkar.contacts;

import java.sql.Blob;

/**
 * Created by neha on 2/27/2018.
 */

public class Contact {
    String name;
    String number;

    public byte[] getBlob() {
        return blob;
    }

    public void setBlob(byte[] blob) {
        this.blob = blob;
    }

    byte[] blob;

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    String json;
    public boolean isIschecked() {
        return ischecked;
    }

    public void setIschecked(boolean ischecked) {
        this.ischecked = ischecked;
    }

    boolean ischecked;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }


}
