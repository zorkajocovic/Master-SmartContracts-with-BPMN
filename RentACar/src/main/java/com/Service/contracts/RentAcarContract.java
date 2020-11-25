package com.Service.contracts;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

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
    private static final String BINARY = "608060405234801561001057600080fd5b50600080546001600160a01b03191633179055610457806100326000396000f3fe608060405234801561001057600080fd5b50600436106100365760003560e01c80632a4155c41461003b57806398d5fdca146102fd575b600080fd5b6102fb600480360360c081101561005157600080fd5b810190602081018135600160201b81111561006b57600080fd5b82018360208201111561007d57600080fd5b803590602001918460018302840111600160201b8311171561009e57600080fd5b91908080601f0160208091040260200160405190810160405280939291908181526020018383808284376000920191909152509295949360208101935035915050600160201b8111156100f057600080fd5b82018360208201111561010257600080fd5b803590602001918460018302840111600160201b8311171561012357600080fd5b91908080601f0160208091040260200160405190810160405280939291908181526020018383808284376000920191909152509295949360208101935035915050600160201b81111561017557600080fd5b82018360208201111561018757600080fd5b803590602001918460018302840111600160201b831117156101a857600080fd5b91908080601f01602080910402602001604051908101604052809392919081815260200183838082843760009201919091525092958435959094909350604081019250602001359050600160201b81111561020257600080fd5b82018360208201111561021457600080fd5b803590602001918460018302840111600160201b8311171561023557600080fd5b91908080601f0160208091040260200160405190810160405280939291908181526020018383808284376000920191909152509295949360208101935035915050600160201b81111561028757600080fd5b82018360208201111561029957600080fd5b803590602001918460018302840111600160201b831117156102ba57600080fd5b91908080601f016020809104026020016040519081016040528093929190818152602001838380828437600092019190915250929550610317945050505050565b005b610305610388565b60408051918252519081900360200190f35b855161032a90600190602089019061038e565b50845161033e90600290602088019061038e565b506004839055815161035790600590602085019061038e565b50835161036b90600390602087019061038e565b50805161037f90600690602084019061038e565b50505050505050565b60045490565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106103cf57805160ff19168380011785556103fc565b828001600101855582156103fc579182015b828111156103fc5782518255916020019190600101906103e1565b5061040892915061040c565b5090565b5b80821115610408576000815560010161040d56fea2646970667358221220d125a1a7a1b1f9d713f0fd576283f4c26014d1ea1ca1d0d4937d1c48091c0f7c64736f6c63430007000033";

    public static final String FUNC_GETPRICE = "getPrice";

    public static final String FUNC_SETCONTRACT = "setContract";

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

    public static RemoteCall<RentAcarContract> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(RentAcarContract.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    public static RemoteCall<RentAcarContract> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(RentAcarContract.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<RentAcarContract> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(RentAcarContract.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<RentAcarContract> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(RentAcarContract.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public RemoteCall<TransactionReceipt> getPrice() {
        final Function function = new Function(
                FUNC_GETPRICE, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> setContract(String _name, String _surname, String _dealerCompany, BigInteger _price, String _contractText, String _date) {
        final Function function = new Function(
                FUNC_SETCONTRACT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_name), 
                new org.web3j.abi.datatypes.Utf8String(_surname), 
                new org.web3j.abi.datatypes.Utf8String(_dealerCompany), 
                new org.web3j.abi.datatypes.generated.Int256(_price), 
                new org.web3j.abi.datatypes.Utf8String(_contractText), 
                new org.web3j.abi.datatypes.Utf8String(_date)), 
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
}
