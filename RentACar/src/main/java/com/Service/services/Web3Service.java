package com.Service.services;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.abi.datatypes.Function;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.EthCall;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.Transaction;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.exceptions.TransactionException;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Transfer;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.utils.Convert;
import org.web3j.utils.Convert.Unit;

import com.Service.contracts.RentAcarContract;

import aj.org.objectweb.asm.TypeReference;
import javassist.bytecode.analysis.Type;
import rx.Observable;

@Service
public class Web3Service {

	private Web3j web3j = null;
	private Credentials credentials = null;
	
	public String hello() {
		return "Hello";
	}
	
    //Create and Init the default Web3J connection
    public void init(String provider) {
        System.out.println("Connecting to Ethereum ...");
        this.web3j = Web3j.build(new HttpService(provider));
        System.out.println("Successfuly connected to Ethereum");
        
      //0xf3e6721B0974fDd801F74f59f584cEE9424D476d
        try {
			credentials = WalletUtils.loadCredentials("Zorka.12345", "D:\\Master_projekat\\Master-SmartContracts-with-BPMN\\dealer_wallet.json");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (CipherException e) {
			e.printStackTrace();
		}
    }

    public RentAcarContract deployContract(String _name, 
									    		String _surname, 
									    		String _dealerCompany, 
									    		BigInteger _price, 
									    		String _contractText, 
									    		String _date) {

        String contractAddress = "";

        try {
 			RentAcarContract contract = RentAcarContract.deploy(
 									            		web3j,
 									                    credentials,
 									                    new DefaultGasProvider(), 
 									                    _name, 
 									                    _surname, 
 									                    _dealerCompany, 
 									                    _price, 
 									                    _contractText, 
 									                    _date)
 														.send();

            contractAddress = contract.getContractAddress();           
                    
            System.out.println("Smart contract deployed to address " + contractAddress);
            System.out.println("Web3 Client Version" + web3j.web3ClientVersion().send().getWeb3ClientVersion());
            return contract;
        } catch (Exception ex) {
            System.out.println("Error occurs");
            return  null;
        }
    }
    
 public RentAcarContract loadContract(String address) {
    	
    	RentAcarContract contract = RentAcarContract.load(address, web3j, credentials, new DefaultGasProvider());
		try {
			System.out.println(contract.getPrice().send().getValue());
		} catch (Exception e) {
			e.printStackTrace();
		}

      	return contract;
    }
 
    //ko salje (credentials) kome -> 0xfDbF136F4781514F9fd55ad556c2c4D496F62ae5
    /* TransactionReceipt transferReceipt = Transfer.sendFunds(
             web3j, credentials,
             "0xfDbF136F4781514F9fd55ad556c2c4D496F62ae5\r\n",  
             BigDecimal.valueOf(1), Unit.WEI)  // 1 wei = 10^-18 Ether
             .sendAsync().get();*/
     //byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, credentials);
     
 /*    EthGetBalance balanceWei = web3j.ethGetBalance("0xf3e6721B0974fDd801F74f59f584cEE9424D476d", DefaultBlockParameterName.LATEST).send();
     System.out.println("balance in wei: " + balanceWei);

     BigDecimal balanceInEther = Convert.fromWei(balanceWei.getBalance().toString(), Unit.ETHER);
     System.out.println("balance in ether: " + balanceInEther);
     */
    
    public void clientDecided(String address) {

    	RentAcarContract contract = RentAcarContract.load(address, 
										    			web3j, 
										    			credentials, 
										    			new DefaultGasProvider());
		try {
			System.out.println(contract.clientApproved(true).send());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
    
    public void changeReadyAndInsertDeposit(boolean ready, Long deposit) {
    	//promeni stanje auta i upisi deposit u contract
    }
    
    public void payDepositAndGetCar() {
    	try {
			TransactionReceipt transferReceipt = Transfer.sendFunds(
			        web3j, credentials,
			        "0xfDbF136F4781514F9fd55ad556c2c4D496F62ae5\r\n",  
			        BigDecimal.valueOf(1), Unit.WEI)  // 1 wei = 10^-18 Ether
			        .sendAsync().get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransactionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
   
    public void carReturned(boolean returned) {
    	
    }
    
    public void clientPayed(boolean payed) {
    	
    }
}
