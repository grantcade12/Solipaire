package com.example.solipaire;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface AccountDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Account userAccount);

    @Delete
    void delete(Account userAccount);

    @Update
    void update(Account userAccount);

    @Query("SELECT * FROM account")
    LiveData<List<Account>> getAllAccounts();

    @Query("SELECT * FROM account WHERE username LIKE :user AND password LIKE :pass Limit 1")
    LiveData<Account> findByLogin(String user, String pass);
}
