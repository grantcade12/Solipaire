package com.example.solipaire;
import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class AccountRepository {
    private AccountDao acctDao;
    private LiveData<List<Account>> allAccts;

    AccountRepository(Application application) {
        AppDatabase db = AppDatabase.getDB(application);
        acctDao = db.accountDao();
        allAccts = acctDao.getAllAccounts();
    }

    LiveData<List<Account>> getAllAccounts() {
        return allAccts;
    }

    LiveData<Account> findByLogin(Account acct) {
        return acctDao.findByLogin(acct.getUsername(), acct.getPassword());
    }

    void insert(Account acct) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
           acctDao.insert(acct);
        });
    }

    void delete(Account acct) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            acctDao.delete(acct);
        });
    }

    void update(Account acct) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            acctDao.update(acct);
        });
    }
}
