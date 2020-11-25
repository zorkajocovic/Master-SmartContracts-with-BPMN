package com.Service.client.services;

import java.util.Date;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class Payment implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
		execution.getProcessEngineServices().getRuntimeService()
		.createMessageCorrelation("client payed msg")
		.setVariable("payedDate", new Date().toLocaleString())
		.correlate();
		System.out.print("Received client payment!!!");		
	}
}
