package com.example.solipaire.data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Account {
    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo
    public String username;

    @ColumnInfo
    public String password;

    public Account(@NonNull String username, @NonNull String password) {
        this.username = username;
        this.password = password;
    }

    public int getUid() {
        return uid;
    }
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
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
