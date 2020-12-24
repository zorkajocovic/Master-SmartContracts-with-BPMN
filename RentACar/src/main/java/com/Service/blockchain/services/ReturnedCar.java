package com.Service.blockchain.services;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Service.services.Web3Service;

@Service
public class ReturnedCar implements JavaDelegate {

	@Autowired
	Web3Service web3Service;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {

		//web3Service.carReturned(true);	
		System.out.print("Returned car - blockchain!!!");
	}
}
