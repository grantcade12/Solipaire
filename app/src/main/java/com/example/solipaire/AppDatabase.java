package com.example.solipaire;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//Add entities for stats and game
@Database(entities = {Account.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    //Add dao for Stats and game
    public abstract AccountDao acctDao();
    private static volatile AppDatabase dbInstance;
    private static final int NUMBER_OF_THREADS = 2;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static AppDatabase getDB(Context context) {
        if (dbInstance == null)
            synchronized (AppDatabase.class) {
                if (dbInstance == null) {
                    dbInstance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "Solipaire_database").build();
                }
            }
        return dbInstance;
    }
}
