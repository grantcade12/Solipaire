package com.example.solipaire.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface GameDao {
    //Fill in queries and updates for game data
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Game game);

    @Delete
    void delete(Game game);

    @Update
    void update(Game game);

    @Query("SELECT * FROM game WHERE account_id LIKE :acctId Limit 1")
    LiveData<Game> findActiveGame(int acctId);

    @Query("DELETE FROM game WHERE account_id LIKE :acctId")
    void deleteAllFromAccount(int acctId);
}
