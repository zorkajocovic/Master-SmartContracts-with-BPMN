package com.Service.dealer.services;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class SendDocuments implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {

		execution.getProcessEngineServices().getRuntimeService()
		.createMessageCorrelation("Receive documents")
		.correlate();
		System.out.print("Sent documents!!!");
	}

}
