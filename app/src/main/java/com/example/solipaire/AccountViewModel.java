package com.example.solipaire;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class AccountViewModel extends AndroidViewModel{
    private final AccountRepository acctRepo;
    private LiveData<List<Account>> allAccts;

    public AccountViewModel(@NonNull Application application) {
        super(application);
        acctRepo = new AccountRepository(application);
        allAccts = acctRepo.getAllAccounts();
    }

    LiveData<List<Account>> getAllAccounts() {
        return allAccts;
    }

     public LiveData<Account> getAccount(Account acct) {
        return acctRepo.findByLogin(acct);
     }

     public boolean checkLogin(Account acct) {
        boolean isAcct = false;
        Account acctFound = acctRepo.findByLogin(acct).getValue();
        if(acctFound != null && acctFound.equals(acct)) {
            isAcct = true;
        }
        return isAcct;
     }

    public void insert(Account acct) {
        acctRepo.insert(acct);
        //May not be needed if set to final
        allAccts = acctRepo.getAllAccounts();
    }

    public void delete(Account acct) {
        acctRepo.delete(acct);
        //May not be needed if set to final
        allAccts = acctRepo.getAllAccounts();
    }

    public void update(Account acct) {
        acctRepo.update(acct);
        //May not be needed if set to final
        allAccts = acctRepo.getAllAccounts();
    }
}
