package com.Service.client.services;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

public class SendOrder implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
				
		String customerName = execution.getVariable("customerName").toString();
		String customerSurame = execution.getVariable("customerSurame").toString();
		String type = execution.getVariable("type").toString();
		String model = execution.getVariable("model").toString();
		String dateFrom = execution.getVariable("dateFrom").toString();
		String dateTo = execution.getVariable("dateTo").toString();

		execution.getProcessEngineServices().getRuntimeService()
				.createMessageCorrelation("sent order")
				.setVariable("customerName", customerName)
				.setVariable("customerSurame", customerSurame)
				.setVariable("type", type)
				.setVariable("model", model)
				.setVariable("dateFrom", dateFrom)
				.setVariable("dateTo", dateTo)
				.correlate();
		
		System.out.print("Sent order!!!");
	}

}
