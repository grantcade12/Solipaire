package com.example.solipaire.data;
import android.app.Application;

import androidx.lifecycle.LiveData;


public class PlayerRepository {
    private final PlayerDao playerDao;

    public PlayerRepository(Application application) {
        AppDatabase db = AppDatabase.getDB(application);
        playerDao = db.playerDao();
    }

    public LiveData<Player> findPlayerById(String playerId) {
        return playerDao.findPlayerById(playerId);
    }

    public void insert(Player player) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            playerDao.insert(player);
        });
    }

    public void delete(Player player) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            playerDao.delete(player);
        });
    }

    public void update(Player player) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            playerDao.update(player);
        });
    }
}
