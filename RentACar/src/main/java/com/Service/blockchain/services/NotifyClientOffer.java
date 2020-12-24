package com.Service.blockchain.services;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class NotifyClientOffer implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {

		String contractText = execution.getVariable("contractText").toString();
		String date = execution.getVariable("date").toString();
		String model = execution.getVariable("model").toString();
		String type = execution.getVariable("type").toString();
		Long price = (Long) execution.getVariable("price");
		Long deposit = (Long) execution.getVariable("deposit");
		String dealerCompany = (String) execution.getVariable("dealerCompany");
		String contractAddress = (String) execution.getVariable("contractAddress");

		execution.getProcessEngineServices().getRuntimeService()
				.createMessageCorrelation("rec. offer")
				.setVariable("contractText", contractText)
				.setVariable("date", date)
				.setVariable("model", model)
				.setVariable("type", type)
				.setVariable("price", price)
				.setVariable("deposit", deposit)
				.setVariable("dealerCompany", dealerCompany)
				.correlate();
		
		System.out.print("Sent offer from blockchain to client!!!");
	}

}
