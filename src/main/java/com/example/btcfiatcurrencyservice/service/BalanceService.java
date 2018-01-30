package com.example.btcfiatcurrencyservice.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Currency;

import org.springframework.stereotype.Service;

import com.example.btcfiatcurrencyservice.response.BalanceResponse;

@Service
public class BalanceService {

    private final BPIService bpiService;
    private final BTCService btcService;

    public BalanceService(BPIService bpiService,
            BTCService btcService) {
        this.bpiService = bpiService;
        this.btcService = btcService;
    }

    public BalanceResponse getBalance(String address, Currency currency) {
        double btcBalance = btcService.getBtcBalance(address);
        double bpi = bpiService.getBPI(currency);
        BigDecimal fiatAmount = BigDecimal.valueOf(bpi * btcBalance).setScale(2, RoundingMode.DOWN);
        return new BalanceResponse(address, currency.getCurrencyCode(), btcBalance, fiatAmount.doubleValue());
    }

}
