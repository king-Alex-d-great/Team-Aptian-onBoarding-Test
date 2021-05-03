package com.girlcoder.technicaltest.service.impl;

import com.girlcoder.technicaltest.model.Account;
import com.girlcoder.technicaltest.model.User;
import com.girlcoder.technicaltest.respository.AccountRepository;
import com.girlcoder.technicaltest.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account createAccount(String accountNumber, User user) {
        var account = new Account();
        account.setAccountName(user.getUsername());
        account.setAccountHolder(user);
        account.setAccountNumber(accountNumber);
        return accountRepository.save(account);
    }

    @Override
    public Account getAccountByAccountNumber(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber);
    }

    @Override
    public void fundAccount(Account account, double amount) {
        account.setAccountBalance(account.getAccountBalance() + amount);
        accountRepository.save(account);
    }
}
