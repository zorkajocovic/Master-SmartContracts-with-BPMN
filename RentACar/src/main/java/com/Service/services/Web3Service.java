package com.Service.services;

import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.DefaultGasProvider;

import com.Service.contracts.RentAcarContract;

@Service
public class Web3Service {
/*
	  private Web3j web3j = Web3j.build(new HttpService());

	    //Create and Init the default Web3J connection
	    public void init(String provider) {
	        this.web3j = Web3j.build(new HttpService(provider));
	    }

	    public RentAcarContract getDeployedContractAddress() {

	        String contractAddress = "";

	        try {
	        	 Credentials credentials = WalletUtils.loadCredentials(
	                     "Zorka.12345",
	                     "D:\\Master_projekat\\Master-SmartContracts-with-BPMN\\UTC--2020-09-27T08-16-03.576000000Z--f3e6721b0974fdd801f74f59f584cee9424d476d.json");
	             System.out.println("Credentials loaded");
	             
	 			RentAcarContract contract = RentAcarContract.deploy(
	 									            		web3j,
	 									                    credentials,
	 									                    new DefaultGasProvider()).send();

	            contractAddress = contract.getContractAddress();
	            System.out.println("Smart contract deployed to address " + contractAddress);
	            return contract;

	        } catch (Exception ex) {
	            System.out.println(Constants.PLEASE_SUPPLY_REAL_DATA);
	            return null;
	        }
	    }

	  */
}
