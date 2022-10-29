package com.example.card1.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CardDTO {

    private String frontNumbers;
    private String cardHolder;
    private String expirationDate;
    private String cardName;
    private String amount;

}
