package com.bank;

import java.util.ArrayList;

public class Bank {
    private int id;
    private ArrayList<User> users;
    private ArrayList<Account> accounts;

    public Bank() {
        this.users = new ArrayList<>();
        this.accounts = new ArrayList<>();
        this.id = 0;
    }

    public int requestId() {
        return id++;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public void addUser(User user) {
        this.users.add(user);
    }

    public void addAccount(Account account) {
        this.accounts.add(account);
    }
}
