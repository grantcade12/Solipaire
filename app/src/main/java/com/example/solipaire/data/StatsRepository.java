/*package com.example.solipaire.data;
import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class StatsRepository {
    private StatsDao statsDao;
    private LiveData<List<Stats>> allStats;

    public StatsRepository(Application application) {
        AppDatabase db = AppDatabase.getDB(application);
        statsDao = db.statsDao();
        allAccts = acctDao.getAllAccounts();
    }

    public LiveData<List<Account>> getAllAccounts() {
        return allAccts;
    }

    public LiveData<Account> findByLogin(Account acct) {
        return acctDao.findByLogin(acct.getUsername(), acct.getPassword());
    }

    public void insert(Account acct) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            acctDao.insert(acct);
        });
    }

    public void delete(Account acct) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            acctDao.delete(acct);
        });
    }

    public void update(Account acct) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            acctDao.update(acct);
        });
    }
}

 */
