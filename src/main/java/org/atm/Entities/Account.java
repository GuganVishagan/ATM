package org.atm.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter

public class Account {
    private String accountId;
    private String accountPassword;
    private String accountHolderName;
    private List<Card> cards;
    private double accountBalance;

    public Account(String accountId, String accountPassword, String accountHolderName, double accountBalance) {
        this.accountId = accountId;
        this.accountPassword = accountPassword;
        this.accountHolderName = accountHolderName;
        this.cards = new ArrayList<>();
        this.accountBalance = accountBalance;
    }

    public boolean withdraw(double amount) {
        if(accountBalance >= amount) {
            accountBalance -= amount;
            return true;
        }
        return false;
    }

    public void deposit(double amount) {
        if(amount > 0)
            accountBalance += amount;
        else
            System.out.println("Please enter a positive amount!");
    }
}
