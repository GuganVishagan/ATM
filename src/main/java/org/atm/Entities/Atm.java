package org.atm.Entities;

import lombok.Getter;
import lombok.Setter;
import org.atm.State.AtmState;
import org.atm.State.IdleState;

@Getter
@Setter
public class Atm {
    private AtmState currentState;

    private Card currentCard;

    public Atm() {
        currentState = new IdleState();
    }

    public void setState(AtmState state) {
        currentState = state;
    }

    public void insertCard(Card card) {
        currentState.insertCard(this, card);
    }

    public void enterPin(String cardPin) {
        currentState.enterPin(this, cardPin);
    }

    public void depositCash(double amount) {
        currentState.depositCash(this, amount);
    }

    public void checkBalance() {
        currentState.checkBalance(this);
    }

    public void withDrawCash(double amount) {
        currentState.withDrawCash(this, amount);
    }

    public void ejectCard() {
        currentState.ejectCard(this);
    }
}
