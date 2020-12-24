package com.Service.blockchain.services;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class NotifyDealerAcceptedOffer implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
		boolean accepted = (boolean) execution.getVariable("accepted");
		//web3Service.clientDecided(accepted);
		execution.getProcessEngineServices().getRuntimeService()
		.createMessageCorrelation("dealer receives acceptation")
		.setVariable("accepted", accepted)
		.correlate();
		
	}
}
