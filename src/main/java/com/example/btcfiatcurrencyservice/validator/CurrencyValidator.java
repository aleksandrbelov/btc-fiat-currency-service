package com.example.btcfiatcurrencyservice.validator;

import java.util.Currency;

import javax.ws.rs.BadRequestException;

import org.springframework.stereotype.Component;

@Component
public class CurrencyValidator {

    public Currency validate(String currency){
        try {
            return Currency.getInstance(currency);
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("Currency code is not valid!");
        }
    }
}
