package com.example.card1.controller;

import com.example.card1.dto.MistakeDTO;
import com.example.card1.exception.WebException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AdviceController {

    @ExceptionHandler(value = WebException.class)
    public ResponseEntity<MistakeDTO> requestExceptionHandler(WebException ex){
        MistakeDTO mistakeDTO = MistakeDTO.builder().message(ex.getMessage()).build();
        return new ResponseEntity<>(mistakeDTO, HttpStatus.BAD_REQUEST);
    }

}
