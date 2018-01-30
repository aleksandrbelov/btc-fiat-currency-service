package com.example.btcfiatcurrencyservice.resource;

import java.security.NoSuchAlgorithmException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.example.btcfiatcurrencyservice.service.BalanceService;
import com.example.btcfiatcurrencyservice.validator.AddressValidator;
import com.example.btcfiatcurrencyservice.validator.CurrencyValidator;

@Path("/api/balance")
@Produces(MediaType.APPLICATION_JSON)
public class BalanceResource {

    private final BalanceService balanceService;
    private final AddressValidator addressValidator;
    private final CurrencyValidator currencyValidator;

    public BalanceResource(BalanceService balanceService,
            AddressValidator addressValidator,
            CurrencyValidator currencyValidator) {
        this.balanceService = balanceService;
        this.addressValidator = addressValidator;
        this.currencyValidator = currencyValidator;
    }

    @GET
    public Response currentBalance(@QueryParam("address") String address,
            @QueryParam("currency") String currency) throws NoSuchAlgorithmException {
        addressValidator.validateAddress(address);
        return Response.ok(balanceService.getBalance(address, currencyValidator.validate(currency))).build();
    }

}
