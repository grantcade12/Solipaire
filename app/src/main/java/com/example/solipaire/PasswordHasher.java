package com.example.solipaire;

import android.util.Log;

import androidx.annotation.NonNull;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordHasher {
    public PasswordHasher(){}

    public String HashPassword(@NonNull String password){
        try {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(password.getBytes());
            byte[] bytes = m.digest();
            StringBuilder s = new StringBuilder();
            for (byte aByte : bytes) {
                s.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }
            Log.i(null,"Password successfully hashed");
            return s.toString();
        } catch (NoSuchAlgorithmException e){
            Log.e(null,"Error hashing password");
            throw new RuntimeException("Error hashing password");
        }
    }
}
