package com.example.btcfiatcurrencyservice.service;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;
import static com.example.btcfiatcurrencyservice.service.BPIService.API_COINDESK_COM;

import java.net.URI;
import java.util.Currency;
import java.util.Map;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.example.btcfiatcurrencyservice.response.BPIResponse;

import jersey.repackaged.com.google.common.collect.ImmutableMap;

@RunWith(MockitoJUnitRunner.class)
public class BPIServiceTest {

    @InjectMocks
    private BPIService bpiService;
    @Mock
    private RestClientService restClientService;

    @Test
    public void getBPI() {
        String usd = "USD";
        URI uri = UriBuilder.fromPath(API_COINDESK_COM).build(usd);

        BPIResponse bpiResponse = new BPIResponse();
        ImmutableMap<String, Map<String, String>> bpiMap = new ImmutableMap.Builder<String, Map<String, String>>()
                .put(usd, ImmutableMap.of("rate_float", "123.45")).build();
        bpiResponse.setBpi(bpiMap);

        Response response = Mockito.mock(Response.class);
        when(restClientService.get(uri)).thenReturn(response);
        when(response.readEntity(BPIResponse.class)).thenReturn(bpiResponse);

        Double bpi = bpiService.getBPI(Currency.getInstance(usd));

        MatcherAssert.assertThat(bpi, equalTo(Double.parseDouble("123.45")));
    }
}