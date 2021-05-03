package com.girlcoder.technicaltest.service;

import com.girlcoder.technicaltest.model.Account;
import com.girlcoder.technicaltest.model.User;

public interface AccountService {
    Account createAccount(String accountNumber, User user);
    Account getAccountByAccountNumber(String accountNumber);
    void fundAccount(Account account,double amount);
}
