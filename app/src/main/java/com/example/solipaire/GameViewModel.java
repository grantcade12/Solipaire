package com.example.solipaire;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


public class GameViewModel extends AndroidViewModel{
    private final GameRepository gameRepo;

    public GameViewModel(@NonNull Application application) {
        super(application);
        gameRepo = new GameRepository(application);
    }

    public LiveData<Game> findActiveGame(int acctId) {
        return gameRepo.findActiveGame(acctId);
    }

    public void insert(Game game) {
        deleteAllFromAccount(game.getAccountId());
        gameRepo.insert(game);
    }

    public void delete(Game game) {
        gameRepo.delete(game);
    }

    public void update(Game game) {
        gameRepo.update(game);
    }

    public void deleteAllFromAccount(int acctId) {
        gameRepo.deleteAllFromAccount(acctId);
    }
}
