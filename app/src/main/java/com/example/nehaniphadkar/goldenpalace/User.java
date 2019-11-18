package com.example.nehaniphadkar.goldenpalace;

/**
 * Created by Afzal on 12-Apr-18.
 */

public class User {

    public String name;
    public String email;

    // Default constructor required for calls to
    // DataSnapshot.getValue(User.class)
    public User() {
    }

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
