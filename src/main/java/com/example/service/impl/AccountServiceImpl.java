package com.example.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.exception.AccountAlreadyExistException;
import com.example.exception.InvalidCredentialsException;
import com.example.repository.AccountRepository;
import com.example.service.AccountService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    public void validateUsername(String username) {
        if (username.trim().isBlank()) {
            throw new IllegalArgumentException("Username input is invalid!");
        }

    }

    public void validatePassword(String password) {
        if (password.trim().isBlank() || password.length() < 4) {
            throw new IllegalArgumentException("Password input is invalid!");
        }
    }

    public void validateUserExists(String username) {
        if (accountRepository.findByUsername(username).isPresent()) {
            throw new AccountAlreadyExistException("Username Taken!");
        }
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public Account registerAccount(Account account) {
        validateUsername(account.getUsername());
        validatePassword(account.getPassword());
        validateUserExists(account.getUsername());
        Account accountCreated = accountRepository.saveAndFlush(account);
        return accountCreated;
    }

    @Override
    public Account login(String username, String password) {
        Optional<Account> optionalAccount = accountRepository.findByUsernameAndPassword(username, password);
        if (optionalAccount.isEmpty()) {
            throw new InvalidCredentialsException("Invalid username/password provided!");
        }
        return optionalAccount.get();
    }

}
