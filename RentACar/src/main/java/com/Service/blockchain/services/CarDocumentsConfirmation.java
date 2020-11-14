package com.Service.blockchain.services;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@Service
public class CarDocumentsConfirmation implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
		System.out.print("Car Documents Confirmation!!!");

	}
}
