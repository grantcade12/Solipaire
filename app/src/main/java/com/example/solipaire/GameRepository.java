package com.example.solipaire;
import android.app.Application;

import androidx.lifecycle.LiveData;


public class GameRepository {
    private final GameDao gameDao;

    GameRepository(Application application) {
        AppDatabase db = AppDatabase.getDB(application);
        gameDao = db.gameDao();
    }

    LiveData<Game> findActiveGame(int acctId) {
        return gameDao.findActiveGame(acctId);
    }

    void insert(Game game) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            gameDao.insert(game);
        });
    }

    void delete(Game game) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            gameDao.delete(game);
        });
    }

    void update(Game game) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            gameDao.update(game);
        });
    }

    void deleteAllFromAccount(int acctId) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            gameDao.deleteAllFromAccount(acctId);
        });
    }
}
