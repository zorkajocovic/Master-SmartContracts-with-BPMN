package com.Service.dealer.services;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class ReadyCar implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {

		execution.getProcessEngineServices().getRuntimeService()
		.createMessageCorrelation("car is ready - blockchain")
		.correlate();
	}

}
