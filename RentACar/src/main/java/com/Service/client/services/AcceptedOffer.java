package com.Service.client.services;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@Service
public class AcceptedOffer implements JavaDelegate {
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
		boolean accepted = (boolean) execution.getVariable("agree");
		//String blockchainProcessId = (String) execution.getVariable("blockchainProcessId");
		
		if(accepted)
			System.out.print("Accepted Offer!!!");
		else
			System.out.print("Refused Offer!!!");

		execution.getProcessEngineServices()
				.getRuntimeService()
				.createMessageCorrelation("rec mes agreed")
				.correlateWithResult();
		
	}

}
