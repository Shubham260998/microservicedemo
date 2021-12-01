package com.example.limit.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.limit.service.configration.Configration;
import com.example.limit.service.model.Limit;


@RestController
public class LimitController {
	
	@Autowired
	private Configration config;
	
	@GetMapping("/limits")
	public Limit retriveValue() {
		
		return new Limit(config.getMinimum(),config.getMaximum());
		
	}

}
