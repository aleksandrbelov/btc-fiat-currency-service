package com.example.btcfiatcurrencyservice.validator;

import java.security.NoSuchAlgorithmException;

import javax.ws.rs.BadRequestException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AddressValidatorTest {

    @InjectMocks
    private AddressValidator addressValidator;

    @Test
    public void testValidAddress() throws NoSuchAlgorithmException {
        addressValidator.validateAddress("1AGNa15ZQXAZUgFiqJ2i7Z2DPU2J6hW62i");
    }

    @Test(expected = BadRequestException.class)
    public void testInvalidAddressWithZero() throws NoSuchAlgorithmException {
        addressValidator.validateAddress("1AGNa15ZQXA0ZUgFiqJ2i7Z2DPU2J6hW62i");
    }

    @Test(expected = BadRequestException.class)
    public void testInvalidAddressWithO() throws NoSuchAlgorithmException {
        addressValidator.validateAddress("1AGNa15ZQXAOZUgFiqJ2i7Z2DPU2J6hW62i");
    }

    @Test(expected = BadRequestException.class)
    public void testInvalidAddressWithI() throws NoSuchAlgorithmException {
        addressValidator.validateAddress("1AGNa15ZQXAIZUgFiqJ2i7Z2DPU2J6hW62i");
    }

    @Test(expected = BadRequestException.class)
    public void testInvalidAddressWithl() throws NoSuchAlgorithmException {
        addressValidator.validateAddress("1AGNa15ZQXAlZUgFiqJ2i7Z2DPU2J6hW62i");
    }
}