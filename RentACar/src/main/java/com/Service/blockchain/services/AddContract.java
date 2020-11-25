package com.Service.blockchain.services;

import java.math.BigInteger;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

import com.Service.contracts.RentAcarContract;
import com.Service.services.Web3Service;

@Service
public class AddContract implements JavaDelegate {

	@Autowired
	Web3Service web3Service;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
		System.out.print("Uspjesno startovan bloackchain proces!!!");
		web3Service.init("https://kovan.infura.io/v3/bf0fc3e14b23466e9c0cea210b78abdc");
		//RentAcarContract contract = web3Service.getDeployedContract();
		
		String customerName = execution.getVariable("customerName").toString();
		String customerSurname = execution.getVariable("customerSurame").toString();
		String dealerCompany = execution.getVariable("dealerCompany").toString();
		String contractText = execution.getVariable("contractText").toString();
		String date = execution.getVariable("date").toString();
		String model = execution.getVariable("model").toString();
		String type = execution.getVariable("type").toString();
		BigInteger price = BigInteger.valueOf((Long)execution.getVariable("price"));

		//contract.setContract(customerName, customerSurname, dealerCompany, price, contractText, date).send();
	}
}
