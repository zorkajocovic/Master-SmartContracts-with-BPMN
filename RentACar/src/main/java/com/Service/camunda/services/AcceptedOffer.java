package com.Service.camunda.services;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.runtime.Execution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AcceptedOffer implements JavaDelegate {
	
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
		System.out.print("Accepted Offer!!!");
		
	}

}
