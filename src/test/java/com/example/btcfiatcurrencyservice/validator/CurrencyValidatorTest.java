package com.example.btcfiatcurrencyservice.validator;

import java.util.Currency;

import javax.ws.rs.BadRequestException;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CurrencyValidatorTest {

    @InjectMocks
    private CurrencyValidator currencyValidator;

    @Test
    public void testValidCode() {
        Currency currency = currencyValidator.validate("USD");
        MatcherAssert.assertThat(currency.getCurrencyCode(), Matchers.equalTo("USD"));
    }

    @Test(expected = BadRequestException.class)
    public void testInvalidCode() {
        currencyValidator.validate("ASD");
    }
}