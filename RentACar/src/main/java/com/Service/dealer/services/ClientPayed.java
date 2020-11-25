package com.Service.dealer.services;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class ClientPayed implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {

		execution.getProcessEngineServices().getRuntimeService()
		.createMessageCorrelation("add to blockchain payed info")
		.correlate();
		System.out.print("Client payed - added to blockchain!!!");
	}

}
