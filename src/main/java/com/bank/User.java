package com.bank;

import java.util.ArrayList;

public class User {
    int id;
    String name;
    ArrayList<Account> accounts;
    Bank bank;

    public User(String name, Bank bank) {
        this.bank = bank;
        this.id = this.bank.requestId();
        this.name = name;
        this.accounts = new ArrayList<>();
    }

    public void addAccount(Account account) {
        this.accounts.add(account);
    }
}
