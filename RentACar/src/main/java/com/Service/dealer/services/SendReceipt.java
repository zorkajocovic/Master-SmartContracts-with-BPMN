package com.Service.dealer.services;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class SendReceipt implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {

		Long totalPrice = (Long) execution.getVariable("totalPrice");
		
		execution.getProcessEngineServices().getRuntimeService()
		.createMessageCorrelation("receipt for a car is received")
		.setVariable("totalPrice", totalPrice)
		.correlate();
		
		System.out.print("Sent receipt with totalPrice!!!");
	}
}
