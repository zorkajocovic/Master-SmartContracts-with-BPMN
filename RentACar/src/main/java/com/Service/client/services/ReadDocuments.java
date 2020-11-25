package com.Service.client.services;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class ReadDocuments implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {

		execution.getProcessEngineServices().getRuntimeService()
		.createMessageCorrelation("Receive read docs")
		.correlate();
		System.out.print("Read documents!!!");

	}

}
