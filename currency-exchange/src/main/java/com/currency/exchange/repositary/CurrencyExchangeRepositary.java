package com.currency.exchange.repositary;

import org.springframework.data.jpa.repository.JpaRepository;

import com.currency.exchange.model.CurrencyExchange;

public interface CurrencyExchangeRepositary 
extends JpaRepository<CurrencyExchange, Long>{
	
	public CurrencyExchange findByFromAndTo(String from, String to);

}
