package com.Service.client.services;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

public class RefusedOffer implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		System.out.print("Refused Offer!!!");

		
	}

}
