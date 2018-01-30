package com.example.btcfiatcurrencyservice.response;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import jersey.repackaged.com.google.common.collect.ImmutableMap;

public class BalanceResponse {
    private String address;
    private Map<String, Double> balance;
    private String timestamp;

    public BalanceResponse() {
    }

    public BalanceResponse(String address, String currencyCode, double btcBalance, double fiatAmount) {
        this.address = address;
        this.balance = ImmutableMap.of("BTC", btcBalance, currencyCode, fiatAmount);
        this.timestamp = ZonedDateTime.now().format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
    }

    public String getAddress() {
        return address;
    }

    public Map<String, Double> getBalance() {
        return balance;
    }

    public String getTimestamp() {
        return timestamp;
    }
}
