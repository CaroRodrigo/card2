package com.example.card1.controller;

import com.example.card1.dto.CardDTO;
import com.example.card1.exception.WebException;
import com.example.card1.service.impl.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/card")
public class CardController {

    @Autowired
    PaymentService paymentService;

    @PostMapping("/holder")
    public ResponseEntity saveCard(@RequestBody CardDTO cardDTO) throws WebException {

        var cardDTO2 = paymentService.processBuy(cardDTO);
                return ResponseEntity.ok().body(cardDTO2);
    }
}
