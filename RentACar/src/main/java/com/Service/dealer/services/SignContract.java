package com.Service.dealer.services;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class SignContract implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {

		String customerName = execution.getVariable("customerName").toString();
		String customerSurame = execution.getVariable("customerSurame").toString();
		String type = execution.getVariable("type").toString();
		String model = execution.getVariable("model").toString();
		String dealerCompany = execution.getVariable("dealerCompany").toString();
		Long price = (Long) execution.getVariable("price");
		String contractText = execution.getVariable("contractText").toString();
		String date = execution.getVariable("date").toString();

		execution.getProcessEngineServices().getRuntimeService()
				.createMessageCorrelation("Signed contract")
				.setVariable("customerName", customerName)
				.setVariable("customerSurame", customerSurame)
				.setVariable("type", type)
				.setVariable("model", model)
				.setVariable("dealerCompany", dealerCompany)
				.setVariable("price", price)
				.setVariable("contractText", contractText)
				.setVariable("date", date)
				.correlate();
	}
}
