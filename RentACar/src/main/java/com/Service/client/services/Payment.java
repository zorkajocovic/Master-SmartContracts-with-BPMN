package com.Service.client.services;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class Payment implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
		execution.getProcessEngineServices().getRuntimeService()
		.createMessageCorrelation("client payed - blockchain")
		.correlate();
		System.out.print("Client payed!!!");		
	}
}
