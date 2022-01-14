package com.currency.exchange.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.currency.exchange.model.CurrencyExchange;
import com.currency.exchange.repositary.CurrencyExchangeRepositary;

@RestController
public class CurrencyExchnageController {
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private CurrencyExchangeRepositary currencyExchnageRepositary;
	
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyExchange retriveExchangeValue(@PathVariable String from,
												@PathVariable String to) {
	
		
		CurrencyExchange currencyexchange =
				currencyExchnageRepositary.findByFromAndTo(from, to);
		if(currencyexchange == null) {
			throw new RuntimeException("No Result found for "+ from + "to" +to);
		}
		
		String port = environment.getProperty("local.server.port");
		currencyexchange.setEnvironment(port);

		return currencyexchange;
		
				
		
	}

}
