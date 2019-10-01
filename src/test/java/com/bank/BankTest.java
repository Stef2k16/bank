package com.bank;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.util.InputMismatchException;

/**
 * Unit test for simple App.
 */
public class BankTest {
  private Bank bank;

  private void setUp(){
      this.bank = new Bank();
      for(int i = 0; i < 3; i++) {
          this.bank.addUser(new User("User" + (i+1), this.bank));
      }

      for (User u: this.bank.getUsers()) {
          Account account = new Account(u, 100.);
          this.bank.addAccount(account);
          u.addAccount(account);
      }
  }

    @Test
    public void testAddAccount() {
        setUp();
        int lengthBefore = this.bank.getAccounts().size();
        User user = this.bank.getUsers().get(0);
        int lengthBeforeUser = user.accounts.size();
        Account account = new Account(user, 200.);
        this.bank.addAccount(account);
        user.addAccount(account);
        assertEquals(lengthBeforeUser+1, user.accounts.size());
        assertEquals(lengthBefore+1, this.bank.getAccounts().size());
    }

    // Duplicate test for Clone detection
    @Test
    public void testNewAccount() {
        setUp();
        int lengthBefore = this.bank.getAccounts().size();
        User user = this.bank.getUsers().get(0);
        int lengthBeforeUser = user.accounts.size();
        Account account = new Account(user, 400.);
        this.bank.addAccount(account);
        user.addAccount(account);
        assertEquals(lengthBeforeUser+1, user.accounts.size());
        assertEquals(lengthBefore+1, this.bank.getAccounts().size());
    }

    @Test(expected = InputMismatchException.class)
    public void testAddAccountNegativeMoney() {
        setUp();
        User user = this.bank.getUsers().get(0);
        new Account(user, -200.);
    }

    @Test
    public void testAddUser() {
        setUp();
        int lengthBefore = this.bank.getUsers().size();
        this.bank.addUser(new User("TestUser", this.bank));
        assertEquals(lengthBefore + 1, this.bank.getUsers().size());
    }

    @Test
    public void testDepositEnoughMoney() {
        setUp();
        User user1 = this.bank.getUsers().get(0);
        User user2 = this.bank.getUsers().get(1);
        Account acc1 = user1.accounts.get(0);
        Account acc2 = user2.accounts.get(0);
        double user1MoneyBefore = acc1.getMoney();
        double user2MoneyBefore = acc2.getMoney();
        double amount = 10.;
        acc1.deposit(acc2, amount);
        assertEquals(user1MoneyBefore - 10, acc1.getMoney(), 0);
        assertEquals(user2MoneyBefore + 10, acc2.getMoney(), 0);
    }

    @Test(expected = InputMismatchException.class)
    public void testDepositNotEnoughMoney() {
        setUp();
        User user1 = this.bank.getUsers().get(0);
        User user2 = this.bank.getUsers().get(1);
        Account acc1 = user1.accounts.get(0);
        Account acc2 = user2.accounts.get(0);
        double amount = 1000.;
        acc1.deposit(acc2, amount);
    }

    @Test
    public void testWithdrawEnoughMoney() {
        setUp();
        User user = this.bank.getUsers().get(0);
        Account acc = user.accounts.get(0);
        double userMoneyBefore = acc.getMoney();
        double amount = 10.;
        acc.withdraw(amount);
        assertEquals(userMoneyBefore - 10, acc.getMoney(),0);
    }

    @Test(expected = InputMismatchException.class)
    public void testWithdrawNotEnoughMoney() {
        setUp();
        User user = this.bank.getUsers().get(0);
        Account acc = user.accounts.get(0);
        double amount = 1000.;
        acc.withdraw(amount);
    }
}
