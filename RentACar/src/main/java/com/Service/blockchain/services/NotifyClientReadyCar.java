package com.Service.blockchain.services;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class NotifyClientReadyCar implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
		execution.getProcessEngineServices().getRuntimeService()
		.createMessageCorrelation("receive ready car")
		.correlate();
	}

}
