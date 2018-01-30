package com.example.btcfiatcurrencyservice.service;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;
import static com.example.btcfiatcurrencyservice.service.BTCService.CHAIN_SO_API;

import java.net.URI;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.example.btcfiatcurrencyservice.response.BTCResponse;

import jersey.repackaged.com.google.common.collect.ImmutableMap;

@RunWith(MockitoJUnitRunner.class)
public class BTCServiceTest {

    @InjectMocks
    private BTCService btcService;
    @Mock
    private RestClientService restClientService;

    @Test
    public void getBtcBalance() {
        URI uri = UriBuilder.fromPath(CHAIN_SO_API).build("btcAddressTest");
        String btc = "12345.67";

        BTCResponse btcResponse = new BTCResponse();
        btcResponse.setStatus("success");
        btcResponse.setData(ImmutableMap.of("confirmed_balance", btc));

        Response response = Mockito.mock(Response.class);
        when(restClientService.get(uri)).thenReturn(response);
        when(response.readEntity(BTCResponse.class)).thenReturn(btcResponse);

        double btcBalance = btcService.getBtcBalance("btcAddressTest");

        MatcherAssert.assertThat(btcBalance, equalTo(Double.parseDouble(btc)));
    }
}