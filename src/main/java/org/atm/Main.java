package org.atm;

import org.atm.Entities.Account;
import org.atm.Entities.Atm;
import org.atm.Entities.Card;
import org.atm.Enum.CardType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Account myAccount = new Account("3603", "Hello@123", "Vishagan", 100000);

        Card myDebitCard = new Card("123", "Vishagan", LocalDate.of(2028, 4, 1), 321, CardType.DEBIT, myAccount, "0000", "HDFC");
        Card myCreditCard = new Card("546", "Vishagan", LocalDate.of(2029, 4, 1), 391, CardType.CREDIT, myAccount, "1111", "HDFC");
        List<Card> myCards = new ArrayList<>(List.of(myCreditCard, myDebitCard));
        myAccount.setCards(myCards);

        Atm atm = new Atm();

        atm.insertCard(myDebitCard);
        atm.enterPin("0000");
        atm.withDrawCash(11000);
        atm.checkBalance();
        atm.ejectCard();
    }
}