package com.Service.dealer.services;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class SendOffer implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {

		String contractText = execution.getVariable("contractText").toString();
		String date = execution.getVariable("date").toString();
		String model = execution.getVariable("model").toString();
		String type = execution.getVariable("type").toString();
		Long price = (Long) execution.getVariable("price");

		String company = "Reliable renting";
		execution.setVariable("dealerCompany", company);
		execution.getProcessEngineServices().getRuntimeService()
				.createMessageCorrelation("Receive offer")
				.setVariable("contractText", contractText)
				.setVariable("date", date)
				.setVariable("model", model)
				.setVariable("type", type)
				.setVariable("price", price)
				.setVariable("dealerCompany", company)
				.correlate();
		
		System.out.print("Sent offer!!!");
	}
}
