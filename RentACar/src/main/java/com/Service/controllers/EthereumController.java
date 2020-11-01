package com.Service.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.Service.contracts.RentAcarContract;
import com.Service.services.Web3Service;

@RequestMapping(value = "ethereum")
@RestController
public class EthereumController {
	
	@Autowired
	Web3Service web3Service;
	
	/*@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(path = "/initConnection", produces = "application/json")
    public @ResponseBody void initEthereumConnection() {
		
		String provider = "https://kovan.infura.io/v3/bf0fc3e14b23466e9c0cea210b78abdc";   //or local (ganache, geth...)
		web3Service.init(provider);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(path = "/deployContract", produces = "application/json")
    public @ResponseBody ResponseEntity<String> deployContract() {
		
		RentAcarContract contractAddress = web3Service.getDeployedContractAddress();
		return new ResponseEntity<String>(contractAddress.getContractAddress(),  HttpStatus.OK); 
	}*/
}
