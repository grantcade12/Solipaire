package com.example.solipaire;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Account {
    @PrimaryKey
    public String uid;

    @ColumnInfo
    public String username;

    @ColumnInfo
    public String password;

    public String getUid() {
        return uid;
    }
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Account(@NonNull String name, @NonNull String password){
        this.username = name;
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        boolean isEqual = false;
        if (!(o == null || this.getClass() != o.getClass())) {
            Account oAcct = (Account) o;
            isEqual = username.equals(oAcct.getUsername()) && password.equals(oAcct.getPassword());
        }
        return isEqual;
    }

    @Override
    public String toString() {
        return "User " + uid + ": " + username + " password: " + password;
    }


}
