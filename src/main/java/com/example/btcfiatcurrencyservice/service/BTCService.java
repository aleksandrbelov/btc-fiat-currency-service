package com.example.btcfiatcurrencyservice.service;

import java.net.URI;

import javax.ws.rs.core.UriBuilder;

import org.springframework.stereotype.Service;

import com.example.btcfiatcurrencyservice.response.BTCResponse;

@Service
public class BTCService {

    static final String CHAIN_SO_API = "https://chain.so/api/v2/get_address_balance/BTCTEST/{address}";
    private final RestClientService restClientService;

    public BTCService(RestClientService restClientService) {
        this.restClientService = restClientService;
    }

    public double getBtcBalance(String address) {
        URI uri = UriBuilder.fromPath(CHAIN_SO_API).build(address);

        BTCResponse btcResponse = restClientService.get(uri).readEntity(BTCResponse.class);
        return Double.parseDouble(btcResponse.getData().get("confirmed_balance"));
    }
}
