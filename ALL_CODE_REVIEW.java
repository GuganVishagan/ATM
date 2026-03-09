
// ==============================
// FILE: src/main/java/org/atm/Main.java
// ==============================


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

    }
}


// ==============================
// FILE: src/main/java/org/atm/Enum/CardType.java
// ==============================


public enum CardType {
    DEBIT,CREDIT
}



// ==============================
// FILE: src/main/java/org/atm/State/HasCardState.java
// ==============================


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
            System.out.println("VALID PIN!");
            atm.setState(new AuthenticatedState());
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



// ==============================
// FILE: src/main/java/org/atm/State/AtmState.java
// ==============================


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



// ==============================
// FILE: src/main/java/org/atm/State/AuthenticatedState.java
// ==============================


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



// ==============================
// FILE: src/main/java/org/atm/State/IdleState.java
// ==============================


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



// ==============================
// FILE: src/main/java/org/atm/Entities/Card.java
// ==============================



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.atm.Enum.CardType;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
public class Card {
    private String cardId;
    private String cardHolderName;
    private LocalDate expiryDate;
    private int cvv;
    private CardType cardType;
    private Account linkedAccount;
    private String cardPin;
    private String bankName;

    public boolean isValidPin(String enteredPin) {
        return cardPin.equals(enteredPin);
    }
}



// ==============================
// FILE: src/main/java/org/atm/Entities/Atm.java
// ==============================


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



// ==============================
// FILE: src/main/java/org/atm/Entities/Account.java
// ==============================


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
public class Account {
    private String accountId;
    private String accountPassword;
    private String accountHolderName;
    private List<Card> cards;
    private double accountBalance;


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


