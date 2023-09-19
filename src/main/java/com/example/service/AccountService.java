package com.example.service;

import java.util.List;

import com.example.entity.Account;

public interface AccountService {

    List<Account> getAllAccounts();

    Account registerAccount(Account account);

    Account login(String username, String password);
}
