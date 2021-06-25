package org.starcoin.subscribe.api;

import com.alibaba.fastjson.JSON;
import com.thetransactioncompany.jsonrpc2.JSONRPC2Request;
import com.thetransactioncompany.jsonrpc2.JSONRPC2Response;
import com.thetransactioncompany.jsonrpc2.client.JSONRPC2Session;
import com.thetransactioncompany.jsonrpc2.client.JSONRPC2SessionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.starcoin.subscribe.SubscribeApplication;
import org.starcoin.subscribe.bean.Transaction;

import java.net.URL;
import java.util.Arrays;

public class TransactionRPCClient {

    JSONRPC2Session session;

    private static Logger log = LoggerFactory.getLogger(SubscribeApplication.class);

    public TransactionRPCClient(URL baseUrl){
        session = new JSONRPC2Session(baseUrl);
    }

    public Transaction getTransaction(String hash) throws JSONRPC2SessionException {
        int requestID = 0;
        JSONRPC2Request request = new JSONRPC2Request("txpool.pending_txn",Arrays.asList(hash), requestID);
        JSONRPC2Response response = null;
        Transaction transaction = null;

        response = session.send(request);

        if (response.indicatesSuccess()){
            if(response!=null&&response.getResult()!=null){
                String result = response.getResult().toString();
                transaction=JSON.parseObject(result, Transaction.class);
            }
        } else
            log.info(response.getError().getMessage());

        return transaction;
    }

}