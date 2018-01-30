package com.example.btcfiatcurrencyservice.service;

import java.net.URI;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class RestClientService {

    public Response get(URI uri) {
        Response response = ClientBuilder.newClient().target(uri).request(MediaType.APPLICATION_JSON_TYPE).get();
        if (response.getStatus() != HttpStatus.OK.value()) {
            throw new BadRequestException(response.readEntity(String.class));
        }
        return response;
    }
}
