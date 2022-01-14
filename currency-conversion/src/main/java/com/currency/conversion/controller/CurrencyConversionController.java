package com.currency.conversion.controller;

import java.math.BigDecimal;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.currency.conversion.model.CurrencyConversion;

import com.currency.conversion.proxy.CurrencyExchangeServiceProxy;


@RestController
public class CurrencyConversionController {
	
	
//	@Autowired
//	private Environment environment;
	
	@Autowired
	private CurrencyExchangeServiceProxy proxy;
	
	
	@GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion covertCurrency(@PathVariable String from, 
			@PathVariable String to, 
			@PathVariable BigDecimal quantity) {
		
		 //String port= environment.getProperty("local.server.port");
		 
		 
		 HashMap<String,String> urivaribles= new HashMap<String,String>();
		 urivaribles.put("from", from);
		 urivaribles.put("to", to);
		ResponseEntity<CurrencyConversion> currencyConversion = new  RestTemplate().getForEntity
				("http://localhost:8000/currency-exchange/from/USD/to/INR/", 
				CurrencyConversion.class,urivaribles);
		 
		 CurrencyConversion currency= currencyConversion.getBody();
		 CurrencyConversion obj1= new CurrencyConversion();
		 
		 
		
				 obj1.setEnvironment(currency.getEnvironment());
				 obj1.setFrom(from);
				 obj1.setTo(to);
				 obj1.setId(currency.getId());
				 obj1.setQuantity(quantity);
				 obj1.setConversionMultiple(currency.getConversionMultiple());
				 obj1.setTotalCalculatedAmount(quantity.multiply(currency.getConversionMultiple()));
		
		return obj1;
		
	}
	@GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion covertCurrencyUsingFeign(@PathVariable String from, 
			@PathVariable String to, 
			@PathVariable BigDecimal quantity) {
		
//		 String port= environment.getProperty("local.server.port");
//		 
//		 
//		 HashMap<String,String> urivaribles= new HashMap<String,String>();
//		 urivaribles.put("from", from);
//		 urivaribles.put("to", to);
//		ResponseEntity<CurrencyConversion> currencyConversion = new  RestTemplate().getForEntity
//				("http://localhost:8000/currency-exchange/from/USD/to/INR/", 
//				CurrencyConversion.class,urivaribles);
		 
		 CurrencyConversion currency= proxy.retriveExchangeValue(from, to);
		
		
		 CurrencyConversion obj1= new CurrencyConversion();
		 
		 
		
				 obj1.setEnvironment(currency.getEnvironment() + "Feign");
				 obj1.setFrom(from);
				 obj1.setTo(to);
				 obj1.setId(currency.getId());
				 obj1.setQuantity(quantity);
				 obj1.setConversionMultiple(currency.getConversionMultiple());
				 obj1.setTotalCalculatedAmount(quantity.multiply(currency.getConversionMultiple()));
		
		return obj1;
		
	}

}
