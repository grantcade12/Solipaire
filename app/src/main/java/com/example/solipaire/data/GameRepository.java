package com.example.solipaire.data;
import android.app.Application;

import androidx.lifecycle.LiveData;


public class GameRepository {
    private final GameDao gameDao;

    public GameRepository(Application application) {
        AppDatabase db = AppDatabase.getDB(application);
        gameDao = db.gameDao();
    }

    public LiveData<Game> findActiveGame(int acctId) {
        return gameDao.findActiveGame(acctId);
    }

    public void insert(Game game) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            gameDao.insert(game);
        });
    }

    public void delete(Game game) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            gameDao.delete(game);
        });
    }

    public void update(Game game) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            gameDao.update(game);
        });
    }

    public void deleteAllFromAccount(int acctId) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            gameDao.deleteAllFromAccount(acctId);
        });
    }
}
