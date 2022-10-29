package com.example.card1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Card1Application {

	public static void main(String[] args){

		SpringApplication.run(Card1Application.class, args);
	}

}



// documentacion postman

// POST http://localhost:8080/card/holder
// {
//    "frontNumbers":"1111222233334444",
//    "cardHolder":"Rodrigo Caro",
//    "expirationDate":"07/28",
//    "cardName":"visa",
//    "amount":"5000"
// }

// caso triste cualquier dato vacio , nombre de tarjeta mal colocado , tarjeta vencida o formato mal colocado, no valide la cantidad de numeros por que no estoy seguro si todas son iguales en longitud
// con una mano en el corazon trate por horas y horas de subir el proyecto en cloud pero me gano :(

