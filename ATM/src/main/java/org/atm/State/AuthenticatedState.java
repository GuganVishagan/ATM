package org.atm.State;

import org.atm.Entities.Account;
import org.atm.Entities.Atm;
import org.atm.Entities.Card;

public class AuthenticatedState implements AtmState{
    @Override
    public void insertCard(Atm atm, Card card) {
        System.out.println("Transaction in progress");
    }

    @Override
    public void enterPin(Atm atm, String cardPin) {
        System.out.println("Already authenticated");
    }

    @Override
    public void withDrawCash(Atm atm, double amount) {
        Account account = atm.getCurrentCard().getLinkedAccount();
        if(account.withdraw(amount)) {
            System.out.println("Please collect the cash: " + amount);
        }
        else{
            System.out.println("Insufficient Balance");
        }
    }

    @Override
    public void depositCash(Atm atm, double amount) {
        Account account = atm.getCurrentCard().getLinkedAccount();
        account.deposit(amount);
        System.out.println("Amount deposited: " + amount + "Current Balance: " + account.getAccountBalance());
    }

    @Override
    public void checkBalance(Atm atm) {
        Account account = atm.getCurrentCard().getLinkedAccount();
        System.out.println("Current Balance: " + account.getAccountBalance());
    }

    @Override
    public void ejectCard(Atm atm) {
        atm.setState(new IdleState());
        atm.setCurrentCard(null);
        System.out.println("Card Ejected!");
    }
}
