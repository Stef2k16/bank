package com.bank;

import java.util.InputMismatchException;

public class Account {
    private User owner;
    private double money;

    public Account(User owner, double money) {
        this.owner = owner;
        if(money >= 0) {
            this.money = money;
        }
        else {
            throw new InputMismatchException("Money can't be negative");
        }
    }

    public void withdraw(double amount) {
        if (money - amount >= 0) {
            money -= amount;
            System.out.println(amount + " Money withdrawn!");
        }
        else {
            throw new InputMismatchException("Not enough money!");
        }
    }

    public void deposit(Account target, double amount) {
        if (money - amount >= 0) {
            money -= amount;
            target.money += amount;
            System.out.println(amount + " Money transfered!");
        }
        else {
            throw new InputMismatchException("Not enough money!");
        }
    }

    public double getMoney() {
        return money;
    }
}
