package com.example.solipaire.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.solipaire.data.Player;
import com.example.solipaire.data.PlayerRepository;


public class PlayerViewModel extends AndroidViewModel{
    private final PlayerRepository playerRepo;

    public PlayerViewModel(@NonNull Application application) {
        super(application);
        playerRepo = new PlayerRepository(application);
    }

    public LiveData<Player> findPlayerById(String playerId) {
        return playerRepo.findPlayerById(playerId);
    }

    public void insert(Player player) {
        playerRepo.insert(player);
    }

    public void delete(Player player) {
        playerRepo.delete(player);
    }

    public void update(Player player) {
        playerRepo.update(player);
    }
}
