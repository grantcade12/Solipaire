package com.example.solipaire.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface PlayerDao {
    //Fill in queries and updates for game data
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Player player);

    @Delete
    void delete(Player player);

    @Update
    void update(Player player);

    @Query("SELECT * FROM player WHERE pid LIKE :playerId Limit 1")
    LiveData<Player> findPlayerById(String playerId);
}
