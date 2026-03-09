package org.atm.State;

import org.atm.Entities.Atm;
import org.atm.Entities.Card;

public interface AtmState {
    public void insertCard(Atm atm, Card card);
    public void enterPin(Atm atm, String cardPin);
    public void withDrawCash(Atm atm, double amount);
    public void depositCash(Atm atm, double amount);
    public void checkBalance(Atm atm);
    public void ejectCard(Atm arm);
}
