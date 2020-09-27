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
    private static final String BINARY = "608060405234801561001057600080fd5b50604080518082019091526005808252645a6f726b6160d81b602090920191825261003d91600091610077565b50604080518082019091526007808252664a6f636f76696360c81b602090920191825261006c91600191610077565b50600a60035561010a565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106100b857805160ff19168380011785556100e5565b828001600101855582156100e5579182015b828111156100e55782518255916020019190600101906100ca565b506100f19291506100f5565b5090565b5b808211156100f157600081556001016100f6565b610179806101196000396000f3fe608060405234801561001057600080fd5b506004361061002b5760003560e01c806398d5fdca14610030575b600080fd5b6100386100ad565b6040805160208082528351818301528351919283929083019185019080838360005b8381101561007257818101518382015260200161005a565b50505050905090810190601f16801561009f5780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b60008054604080516020601f60026000196101006001881615020190951694909404938401819004810282018101909252828152606093909290918301828280156101395780601f1061010e57610100808354040283529160200191610139565b820191906000526020600020905b81548152906001019060200180831161011c57829003601f168201915b505050505090509056fea2646970667358221220376216b9f06a9babb5a60f6606a971bdca6f402ea7306f3d1347df5397f8451664736f6c63430007000033";

    public static final String FUNC_GETPRICE = "getPrice";

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
