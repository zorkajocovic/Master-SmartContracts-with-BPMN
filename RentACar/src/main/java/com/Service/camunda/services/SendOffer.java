package com.Service.camunda.services;

import java.util.Date;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@Service
public class SendOffer implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
    	String type = execution.getVariable("type").toString();
    	String model = execution.getVariable("model").toString();
    	String customerName = execution.getVariable("customerName").toString();
    	String customerSurname = execution.getVariable("customerSurname").toString();
    	
		String text = "Postovani, saljemo vam predlog iz nase ponude...";

		execution.setVariable("dealerCompany", "Rent For You!");
		execution.setVariable("price", 1000);
		execution.setVariable("date", new Date());
		execution.setVariable("contractText", text);
		execution.setVariable("customerName", customerName);
		execution.setVariable("customerSurname", customerSurname);
		execution.setVariable("sentOffer", true);
	}
}
