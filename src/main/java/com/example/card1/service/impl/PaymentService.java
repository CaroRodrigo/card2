package com.example.card1.service.impl;

import com.example.card1.dto.CardDTO;
import com.example.card1.enums.CardEnum;
import com.example.card1.exception.WebException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Service
public class PaymentService {


    public Map processBuy(CardDTO cardDTO) throws WebException {



        if (cardDTO.getFrontNumbers().isEmpty() || cardDTO.getFrontNumbers().isBlank() ||
                cardDTO.getExpirationDate().isEmpty() || cardDTO.getExpirationDate().isBlank() ||
                cardDTO.getCardHolder().isEmpty() || cardDTO.getCardHolder().isBlank() ||
                cardDTO.getCardName().isEmpty() || cardDTO.getCardName().isBlank()) {


            throw new WebException("Debe ingresar todos los datos requeridos para continuar con la operacion");

        }

            var result = validateCardName(cardDTO.getCardName().toUpperCase());
            validateExpiration(cardDTO.getExpirationDate());

            var amountCard = Double.parseDouble(cardDTO.getAmount());
            var importe = ((result * amountCard) / 100) + amountCard;

            Map<String,Object> mapString = new HashMap<>();
            mapString.put("Interes", result);
            mapString.put("Tarjeta", cardDTO.getCardName());
            mapString.put("Importe", importe);


            return mapString;

    }

    public void validateExpiration(String expirationDate) throws WebException {


        if (year(expirationDate) <= 22) {
            if (year(expirationDate) == 22 && month(expirationDate) <= 10 || year(expirationDate) < 22) {
                throw new WebException("Su tarjeta esta vencida, compruebe si ingreso los datos correctamente ( MM/YY )");
            }
        }

        System.out.println("Su tarjeta es valida para operar");

    }

    public Double validateCardName(String cardName) throws WebException {

        int count = 0;

        CardEnum[] cards = CardEnum.values();
        for (int i = 0; i < cards.length; i++) {
            if (cards[i].toString().equals(cardName)) {
                count = count + 1;
            }
        }
        if (count < 1) {
            throw new WebException("Solo se admiten tarjetas: NARA, VISA o AMEX ");
        }

        LocalDate localDate = LocalDate.now();
        DateTimeFormatter formatterDay = DateTimeFormatter.ofPattern("dd");
        DateTimeFormatter formatterMonth = DateTimeFormatter.ofPattern("MM");
        DateTimeFormatter formatterYear = DateTimeFormatter.ofPattern("YY");

        Double day = Double.valueOf(localDate.format(formatterDay));
        Double month = Double.valueOf(localDate.format(formatterMonth));
        Double year = Double.valueOf(localDate.format(formatterYear));

        switch (cardName) {
            case "VISA":
                Double resultVisa = year/month;
                System.out.println("Tarjeta Visa, tasa por servicio: " + resultVisa +"%");
                return resultVisa;
            case "AMEX":
                Double resultAmex = month*0.1;
                System.out.println("Tarjeta Amex, tasa por servicio: " + resultAmex +"%");
                return resultAmex;
            case "NARA":
                Double resultNara = day*0.5;
                System.out.println("Tarjeta Nara, tasa por servicio : " + resultNara +"%");
                return resultNara;
        }
        return null;
    }


    public Integer year(String expirationDate) {
        String string = expirationDate;
        Integer removeMonth = 2;

        int year = Integer.parseInt(string.substring(string.length() - removeMonth));

        return year;
    }

    public Integer month(String expirationDate) {
        String string = expirationDate;
        Integer save = 2;

        int month = Integer.parseInt(string.substring(0, save));

        return month;
    }



}
