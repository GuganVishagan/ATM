package org.atm.Entities;


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

    public boolean isExpired() {
        return !LocalDate.now().isBefore(expiryDate);
    }
}
