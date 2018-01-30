package com.example.btcfiatcurrencyservice;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.net.URI;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.btcfiatcurrencyservice.response.BalanceResponse;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class BtcFiatCurrencyServiceApplicationTests {

	@Test
	public void testStatusOk() throws Exception {
//        /api/balance?address=<btc-address>&currency=<ISO4217-code>
		String address = "mj6h4U4NYmwNwcY2apGvvXWUBqzArX25oy";
		String currency = "USD";
		URI uri = UriBuilder.fromPath("http://localhost:8080/api/balance").queryParam("address", address)
                .queryParam("currency", currency).build();

		Response response = ClientBuilder.newClient().target(uri).request().get();

		assertThat(response.getStatus(), equalTo(HttpStatus.OK.value()));

		BalanceResponse actual = response.readEntity(BalanceResponse.class);
		assertThat(actual.getAddress(), equalTo(address));
		assertThat(actual.getBalance().containsKey("BTC"), equalTo(true));
		assertThat(actual.getBalance().containsKey("USD"), equalTo(true));
	}

    @Test
    public void testInvalidAddress() throws Exception {
//        /api/balance?address=<btc-address>&currency=<ISO4217-code>
        String address = "mj6h4U40NYmwNwcY2apGvvXWUBqzArX25oy";
        String currency = "USD";
        URI uri = UriBuilder.fromPath("http://localhost:8080/api/balance").queryParam("address", address)
                .queryParam("currency", currency).build();

        Response response = ClientBuilder.newClient().target(uri).request().get();

        assertThat(response.readEntity(String.class), equalTo("BTC address is not valid!"));
    }

    @Test
    public void testInvalidCurrency() throws Exception {
//        /api/balance?address=<btc-address>&currency=<ISO4217-code>
        String address = "mj6h4U4NYmwNwcY2apGvvXWUBqzArX25oy";
        String currency = "ASD";
        URI uri = UriBuilder.fromPath("http://localhost:8080/api/balance").queryParam("address", address)
                .queryParam("currency", currency).build();

        Response response = ClientBuilder.newClient().target(uri).request().get();

        assertThat(response.readEntity(String.class), equalTo("Currency code is not valid!"));
    }

}
