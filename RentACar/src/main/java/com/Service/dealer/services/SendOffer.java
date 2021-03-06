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
		String customerName = execution.getVariable("customerName").toString();
		String customerSurame = execution.getVariable("customerSurame").toString();
		String dateFrom = execution.getVariable("dateFrom").toString();
		String dateTo = execution.getVariable("dateTo").toString();
		
		Long price = (Long) execution.getVariable("price");
		Long deposit = (Long) execution.getVariable("deposit");

		String company = "Reliable renting";
		execution.setVariable("dealerCompany", company);
		
		execution.getProcessEngineServices().getRuntimeService()
				.createMessageCorrelation("initialize blockchain process")
				.setVariable("contractText", contractText)
				.setVariable("date", date)
				.setVariable("model", model)
				.setVariable("type", type)
				.setVariable("price", price)
				.setVariable("customerName", customerName)
				.setVariable("customerSurame", customerSurame)
				.setVariable("dateFrom", dateFrom)
				.setVariable("dateTo", dateTo)
				.setVariable("deposit", deposit)
				.setVariable("dealerCompany", company)
				.correlate();
		
		System.out.print("Sent offer!!!");
	}
}
