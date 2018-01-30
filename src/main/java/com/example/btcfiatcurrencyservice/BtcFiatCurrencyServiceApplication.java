package com.example.btcfiatcurrencyservice;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.example.btcfiatcurrencyservice.resource.BalanceResource;
import com.example.btcfiatcurrencyservice.service.BalanceService;
import com.example.btcfiatcurrencyservice.validator.AddressValidator;
import com.example.btcfiatcurrencyservice.validator.CurrencyValidator;

@SpringBootApplication
public class BtcFiatCurrencyServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BtcFiatCurrencyServiceApplication.class, args);
	}
}

@Component
class GenericExceptionMapper implements ExceptionMapper<BadRequestException> {

	@Override
	public Response toResponse(BadRequestException exception) {
		return Response.serverError().entity(exception.getMessage()).build();
	}
}

@Configuration
class JerseyConfig {

	@Bean
	public BalanceResource balanceResource(BalanceService balanceService,
			AddressValidator addressValidator, CurrencyValidator currencyValidator) {
		return new BalanceResource(balanceService, addressValidator, currencyValidator);
	}

	@Bean
	public ResourceConfig config(BalanceResource balanceResource, GenericExceptionMapper genericExceptionMapper) {
		ResourceConfig resourceConfig = new ResourceConfig();
		resourceConfig.register(balanceResource);
		resourceConfig.register(genericExceptionMapper);
		return resourceConfig;
	}

}