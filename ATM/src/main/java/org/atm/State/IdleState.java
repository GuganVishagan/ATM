package org.atm.State;

import org.atm.Entities.Atm;
import org.atm.Entities.Card;

public class IdleState implements AtmState{

    @Override
    public void insertCard(Atm atm, Card card) {
        atm.setState(new HasCardState());
        atm.setCurrentCard(card);
        System.out.println("Card Inserted");
    }

    @Override
    public void enterPin(Atm atm, String cardPin) {
        System.out.println("Please enter the card first");
    }

    @Override
    public void withDrawCash(Atm atm, double amount) {
        System.out.println("Please enter the card first");
    }

    @Override
    public void depositCash(Atm atm, double amount) {
        System.out.println("Please enter the card first");
    }

    @Override
    public void checkBalance(Atm atm) {
        System.out.println("Please enter the card first");
    }

    @Override
    public void ejectCard(Atm arm) {
        System.out.println("Please enter the card first");
    }
}
