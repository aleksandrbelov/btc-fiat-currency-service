package com.example.btcfiatcurrencyservice.service;

import java.net.URI;
import java.util.Currency;

import javax.ws.rs.core.UriBuilder;

import org.springframework.stereotype.Service;

import com.example.btcfiatcurrencyservice.response.BPIResponse;

@Service
public class BPIService {

    static final String API_COINDESK_COM = "https://api.coindesk.com/v1/bpi/currentprice/{CODE}.json";

    private final RestClientService restClientService;

    public BPIService(RestClientService restClientService) {
        this.restClientService = restClientService;
    }

    public Double getBPI(Currency currency){
        URI uri = UriBuilder.fromPath(API_COINDESK_COM)
                .build(currency.getCurrencyCode());

        BPIResponse bpiResponse = restClientService.get(uri).readEntity(BPIResponse.class);
        String rate = bpiResponse.getBpi().get(currency.getCurrencyCode()).get("rate_float");
        return Double.parseDouble(rate);
    }

}
