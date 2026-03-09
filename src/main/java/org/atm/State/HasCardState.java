package org.atm.State;

import org.atm.Entities.Atm;
import org.atm.Entities.Card;

public class HasCardState implements AtmState {
    @Override
    public void insertCard(Atm atm, Card card) {
        System.out.println("Card has already been inserted state");
    }

    @Override
    public void enterPin(Atm atm, String cardPin) {
        if(atm.getCurrentCard().isValidPin(cardPin)) {
            if(!atm.getCurrentCard().isExpired()) {
                System.out.println("VALID PIN!");
                atm.setState(new AuthenticatedState());
            }
            else {
                System.out.println("Card Expired!");
            }
        }
        else{
            System.out.println("INVALID PIN!");
        }
    }

    @Override
    public void withDrawCash(Atm atm, double amount) {
        System.out.println("Please enter the PIN first!");
    }

    @Override
    public void depositCash(Atm atm, double amount) {
        System.out.println("Please enter the PIN first!");
    }

    @Override
    public void checkBalance(Atm atm) {
        System.out.println("Please enter the PIN first!");
    }

    @Override
    public void ejectCard(Atm arm) {
        System.out.println("Please enter the PIN first!");
    }
}
