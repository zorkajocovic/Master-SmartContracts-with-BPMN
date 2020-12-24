package com.Service.contracts;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Uint;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;
import rx.Observable;
import rx.functions.Func1;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 3.6.0.
 */
public class RentAcarContract extends Contract {
    private static final String BINARY = "60806040523480156200001157600080fd5b5060405162000e6838038062000e68833981810160405260c08110156200003757600080fd5b81019080805160405193929190846401000000008211156200005857600080fd5b9083019060208201858111156200006e57600080fd5b82516401000000008111828201881017156200008957600080fd5b82525081516020918201929091019080838360005b83811015620000b85781810151838201526020016200009e565b50505050905090810190601f168015620000e65780820380516001836020036101000a031916815260200191505b50604052602001805160405193929190846401000000008211156200010a57600080fd5b9083019060208201858111156200012057600080fd5b82516401000000008111828201881017156200013b57600080fd5b82525081516020918201929091019080838360005b838110156200016a57818101518382015260200162000150565b50505050905090810190601f168015620001985780820380516001836020036101000a031916815260200191505b5060405260200180516040519392919084640100000000821115620001bc57600080fd5b908301906020820185811115620001d257600080fd5b8251640100000000811182820188101715620001ed57600080fd5b82525081516020918201929091019080838360005b838110156200021c57818101518382015260200162000202565b50505050905090810190601f1680156200024a5780820380516001836020036101000a031916815260200191505b506040818152602083015192018051929491939192846401000000008211156200027357600080fd5b9083019060208201858111156200028957600080fd5b8251640100000000811182820188101715620002a457600080fd5b82525081516020918201929091019080838360005b83811015620002d3578181015183820152602001620002b9565b50505050905090810190601f168015620003015780820380516001836020036101000a031916815260200191505b50604052602001805160405193929190846401000000008211156200032557600080fd5b9083019060208201858111156200033b57600080fd5b82516401000000008111828201881017156200035657600080fd5b82525081516020918201929091019080838360005b83811015620003855781810151838201526020016200036b565b50505050905090810190601f168015620003b35780820380516001836020036101000a031916815260200191505b506040525050600080546001600160a01b03191633179055508551620003e19060019060208901906200044b565b508451620003f79060029060208801906200044b565b5060058390558151620004129060049060208501906200044b565b508351620004289060039060208701906200044b565b5080516200043e9060069060208401906200044b565b50505050505050620004e7565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106200048e57805160ff1916838001178555620004be565b82800160010185558215620004be579182015b82811115620004be578251825591602001919060010190620004a1565b50620004cc929150620004d0565b5090565b5b80821115620004cc5760008155600101620004d1565b61097180620004f76000396000f3fe6080604052600436106101355760003560e01c8063838f6df9116100ab578063a20673021161006f578063a206730214610461578063a729c92f14610476578063a944b7a61461048b578063cefceaf9146104a0578063d0e30db0146104b5578063daeecfb0146104ca57610135565b8063838f6df9146103cd578063946af6db146103f657806398d5fdca146104225780639900fde714610437578063a035b1fe1461044c57610135565b8063323046b1116100fd578063323046b11461029a5780634cc88ed1146102af5780636a33bf87146102db5780636b258e061461038e5780637a8eac98146103a357806382bbf625146103b857610135565b806302d05d3f1461013a5780630b7f16371461016b57806316e5b189146101995780631f1bd692146101cb57806327e235e314610255575b600080fd5b34801561014657600080fd5b5061014f6104df565b604080516001600160a01b039092168252519081900360200190f35b34801561017757600080fd5b506101976004803603602081101561018e57600080fd5b503515156104ee565b005b3480156101a557600080fd5b50610197600480360360408110156101bc57600080fd5b50803515159060200135610515565b3480156101d757600080fd5b506101e061052c565b6040805160208082528351818301528351919283929083019185019080838360005b8381101561021a578181015183820152602001610202565b50505050905090810190601f1680156102475780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b34801561026157600080fd5b506102886004803603602081101561027857600080fd5b50356001600160a01b03166105ba565b60408051918252519081900360200190f35b3480156102a657600080fd5b506101e06105cc565b610197600480360360408110156102c557600080fd5b506001600160a01b038135169060200135610627565b3480156102e757600080fd5b50610197600480360360208110156102fe57600080fd5b81019060208101813564010000000081111561031957600080fd5b82018360208201111561032b57600080fd5b8035906020019184600183028401116401000000008311171561034d57600080fd5b91908080601f016020809104026020016040519081016040528093929190818152602001838380828437600092019190915250929550610667945050505050565b34801561039a57600080fd5b506101e0610703565b3480156103af57600080fd5b506101e061075e565b3480156103c457600080fd5b506101e06107b9565b3480156103d957600080fd5b506103e2610811565b604080519115158252519081900360200190f35b34801561040257600080fd5b506101976004803603602081101561041957600080fd5b50351515610821565b34801561042e57600080fd5b5061028861083d565b34801561044357600080fd5b506103e2610843565b34801561045857600080fd5b50610288610851565b34801561046d57600080fd5b506103e2610857565b34801561048257600080fd5b506103e2610868565b34801561049757600080fd5b506101e0610877565b3480156104ac57600080fd5b506101e06108d2565b3480156104c157600080fd5b5061028861092c565b3480156104d657600080fd5b506103e2610932565b6000546001600160a01b031681565b600a805463ff000000199215156401000000000264ff000000001990911617919091169055565b600a805460ff191692151592909217909155600955565b6004805460408051602060026001851615610100026000190190941693909304601f810184900484028201840190925281815292918301828280156105b25780601f10610587576101008083540402835291602001916105b2565b820191906000526020600020905b81548152906001019060200180831161059557829003601f168201915b505050505081565b600b6020526000908152604090205481565b6006805460408051602060026001851615610100026000190190941693909304601f810184900484028201840190925281815292918301828280156105b25780601f10610587576101008083540402835291602001916105b2565b6001600160a01b039091166000908152600b6020526040902080549091019055600a805463ff0000001961ff001990911661010017166301000000179055565b604080516020808252835181830152835133937f4208034e4449bb439a4b1ba16ff193bec2c83ec39cd969a629aa475c7f0b80479386939092839283019185019080838360005b838110156106c65781810151838201526020016106ae565b50505050905090810190601f1680156106f35780820380516001836020036101000a031916815260200191505b509250505060405180910390a250565b6003805460408051602060026001851615610100026000190190941693909304601f810184900484028201840190925281815292918301828280156105b25780601f10610587576101008083540402835291602001916105b2565b6008805460408051602060026001851615610100026000190190941693909304601f810184900484028201840190925281815292918301828280156105b25780601f10610587576101008083540402835291602001916105b2565b6002805460408051602060018416156101000260001901909316849004601f810184900484028201840190925281815292918301828280156105b25780601f10610587576101008083540402835291602001916105b2565b600a546301000000900460ff1681565b600a8054911515620100000262ff000019909216919091179055565b60055490565b600a54610100900460ff1681565b60055481565b600a54640100000000900460ff1681565b600a5462010000900460ff1681565b6007805460408051602060026001851615610100026000190190941693909304601f810184900484028201840190925281815292918301828280156105b25780601f10610587576101008083540402835291602001916105b2565b60018054604080516020600284861615610100026000190190941693909304601f810184900484028201840190925281815292918301828280156105b25780601f10610587576101008083540402835291602001916105b2565b60095481565b600a5460ff168156fea2646970667358221220544bd99eeed427cf1c75db3f745a143c4709c71cc7ecb3cfc3e89583505c47c364736f6c63430007000033";

    public static final String FUNC_BALANCES = "balances";

    public static final String FUNC_CARISREADY = "carIsReady";

    public static final String FUNC_CARREADY = "carReady";

    public static final String FUNC_CARRETURNED = "carReturned";

    public static final String FUNC_CARTAKEN = "carTaken";

    public static final String FUNC_CLIENTACCEPTED = "clientAccepted";

    public static final String FUNC_CLIENTAPPROVED = "clientApproved";

    public static final String FUNC_CREATOR = "creator";

    public static final String FUNC_CUSTOMERNAME = "customerName";

    public static final String FUNC_CUSTOMERSURNAME = "customerSurname";

    public static final String FUNC_DATE = "date";

    public static final String FUNC_DEALERCOMPANYNAME = "dealerCompanyName";

    public static final String FUNC_DEPOSIT = "deposit";

    public static final String FUNC_DEPOSITPAYED = "depositPayed";

    public static final String FUNC_FROMDATE = "fromDate";

    public static final String FUNC_GETPRICE = "getPrice";

    public static final String FUNC_NOTARIZEDOCUMENT = "notarizeDocument";

    public static final String FUNC_PAYDEPOSIT = "payDeposit";

    public static final String FUNC_PRICE = "price";

    public static final String FUNC_RETURNCAR = "returnCar";

    public static final String FUNC_TEXT = "text";

    public static final String FUNC_TODATE = "toDate";

    public static final Event NOTARIZED_EVENT = new Event("Notarized", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Utf8String>() {}));
    ;

    @Deprecated
    protected RentAcarContract(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected RentAcarContract(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected RentAcarContract(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected RentAcarContract(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<RentAcarContract> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, String _name, String _surname, String _dealerCompany, BigInteger _price, String _contractText, String _date) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_name), 
                new org.web3j.abi.datatypes.Utf8String(_surname), 
                new org.web3j.abi.datatypes.Utf8String(_dealerCompany), 
                new org.web3j.abi.datatypes.generated.Uint256(_price), 
                new org.web3j.abi.datatypes.Utf8String(_contractText), 
                new org.web3j.abi.datatypes.Utf8String(_date)));
        return deployRemoteCall(RentAcarContract.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<RentAcarContract> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, String _name, String _surname, String _dealerCompany, BigInteger _price, String _contractText, String _date) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_name), 
                new org.web3j.abi.datatypes.Utf8String(_surname), 
                new org.web3j.abi.datatypes.Utf8String(_dealerCompany), 
                new org.web3j.abi.datatypes.generated.Uint256(_price), 
                new org.web3j.abi.datatypes.Utf8String(_contractText), 
                new org.web3j.abi.datatypes.Utf8String(_date)));
        return deployRemoteCall(RentAcarContract.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<RentAcarContract> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String _name, String _surname, String _dealerCompany, BigInteger _price, String _contractText, String _date) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_name), 
                new org.web3j.abi.datatypes.Utf8String(_surname), 
                new org.web3j.abi.datatypes.Utf8String(_dealerCompany), 
                new org.web3j.abi.datatypes.generated.Uint256(_price), 
                new org.web3j.abi.datatypes.Utf8String(_contractText), 
                new org.web3j.abi.datatypes.Utf8String(_date)));
        return deployRemoteCall(RentAcarContract.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<RentAcarContract> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String _name, String _surname, String _dealerCompany, BigInteger _price, String _contractText, String _date) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_name), 
                new org.web3j.abi.datatypes.Utf8String(_surname), 
                new org.web3j.abi.datatypes.Utf8String(_dealerCompany), 
                new org.web3j.abi.datatypes.generated.Uint256(_price), 
                new org.web3j.abi.datatypes.Utf8String(_contractText), 
                new org.web3j.abi.datatypes.Utf8String(_date)));
        return deployRemoteCall(RentAcarContract.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public List<NotarizedEventResponse> getNotarizedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(NOTARIZED_EVENT, transactionReceipt);
        ArrayList<NotarizedEventResponse> responses = new ArrayList<NotarizedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            NotarizedEventResponse typedResponse = new NotarizedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.notary = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.documentHash = (String) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<NotarizedEventResponse> notarizedEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, NotarizedEventResponse>() {
            @Override
            public NotarizedEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(NOTARIZED_EVENT, log);
                NotarizedEventResponse typedResponse = new NotarizedEventResponse();
                typedResponse.log = log;
                typedResponse.notary = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.documentHash = (String) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<NotarizedEventResponse> notarizedEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(NOTARIZED_EVENT));
        return notarizedEventObservable(filter);
    }

    public RemoteCall<TransactionReceipt> balances(String param0) {
        final Function function = new Function(
                FUNC_BALANCES, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(param0)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> carIsReady(Boolean _ready, BigInteger _deposit) {
        final Function function = new Function(
                FUNC_CARISREADY, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Bool(_ready), 
                new org.web3j.abi.datatypes.generated.Uint256(_deposit)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> carReady() {
        final Function function = new Function(
                FUNC_CARREADY, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> carReturned() {
        final Function function = new Function(
                FUNC_CARRETURNED, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> carTaken() {
        final Function function = new Function(
                FUNC_CARTAKEN, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> clientAccepted() {
        final Function function = new Function(
                FUNC_CLIENTACCEPTED, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> clientApproved(Boolean _accepted) {
        final Function function = new Function(
                FUNC_CLIENTAPPROVED, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Bool(_accepted)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> creator() {
        final Function function = new Function(
                FUNC_CREATOR, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> customerName() {
        final Function function = new Function(
                FUNC_CUSTOMERNAME, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> customerSurname() {
        final Function function = new Function(
                FUNC_CUSTOMERSURNAME, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> date() {
        final Function function = new Function(
                FUNC_DATE, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> dealerCompanyName() {
        final Function function = new Function(
                FUNC_DEALERCOMPANYNAME, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> deposit() {
        final Function function = new Function(
                FUNC_DEPOSIT, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> depositPayed() {
        final Function function = new Function(
                FUNC_DEPOSITPAYED, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> fromDate() {
        final Function function = new Function(
                FUNC_FROMDATE, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Type> getPrice() {        
        final Function function = new Function(
        		FUNC_GETPRICE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<TransactionReceipt> notarizeDocument(String _documentHash) {
        final Function function = new Function(
                FUNC_NOTARIZEDOCUMENT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_documentHash)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> payDeposit(String user, BigInteger _price) {
        final Function function = new Function(
                FUNC_PAYDEPOSIT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(user), 
                new org.web3j.abi.datatypes.generated.Uint256(_price)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> price() {
        final Function function = new Function(
                FUNC_PRICE, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> returnCar(Boolean _return) {
        final Function function = new Function(
                FUNC_RETURNCAR, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Bool(_return)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> text() {
        final Function function = new Function(
                FUNC_TEXT, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> toDate() {
        final Function function = new Function(
                FUNC_TODATE, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static RentAcarContract load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new RentAcarContract(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static RentAcarContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new RentAcarContract(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static RentAcarContract load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new RentAcarContract(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static RentAcarContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new RentAcarContract(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static class NotarizedEventResponse {
        public Log log;

        public String notary;

        public String documentHash;
    }
}
