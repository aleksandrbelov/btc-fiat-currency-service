package com.example.btcfiatcurrencyservice.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.example.btcfiatcurrencyservice.response.BalanceResponse;

@RunWith(MockitoJUnitRunner.class)
public class BalanceServiceTest {

    @InjectMocks
    private BalanceService balanceService;
    @Mock
    private BPIService bpiService;
    @Mock
    private BTCService btcService;

    @Test
    public void getBalance() {
        String address = "mj6h4U4NYmwNwcY2apGvvXWUBqzArX25oy";
        Currency currency = Currency.getInstance("USD");
        double btc = 1.25D;
        double bpi = 126.5235D;

        when(bpiService.getBPI(currency)).thenReturn(bpi);
        when(btcService.getBtcBalance(address)).thenReturn(btc);

        BalanceResponse actual = balanceService.getBalance(address, currency);

        assertThat(actual.getAddress(), equalTo(address));
        assertThat(actual.getBalance().get("BTC"), equalTo(btc));
        assertThat(actual.getBalance().get("USD"), equalTo(BigDecimal.valueOf(btc*bpi).setScale(2, RoundingMode.DOWN).doubleValue()));
    }
}