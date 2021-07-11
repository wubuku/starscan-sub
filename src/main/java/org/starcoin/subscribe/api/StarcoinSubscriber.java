package org.starcoin.subscribe.api;

import io.reactivex.Flowable;
import org.starcoin.subscribe.bean.EventNotification;
import org.starcoin.subscribe.bean.Kind;
import org.starcoin.subscribe.bean.PendingTransactionNotification;
import org.web3j.protocol.Web3jService;
import org.web3j.protocol.core.Request;
import org.web3j.protocol.core.methods.response.EthSubscribe;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class StarcoinSubscriber {

    private final Web3jService web3jService;

    public StarcoinSubscriber(Web3jService web3jService) {
        this.web3jService = web3jService;
    }

    public Flowable<PendingTransactionNotification> newPendingTransactionsNotifications() {
        return web3jService.subscribe(
                new Request<>(
                        "starcoin_subscribe",
                        Arrays.asList(Kind.PendingTxn),
                        web3jService,
                        EthSubscribe.class),
                "starcoin_unsubscribe",
                PendingTransactionNotification.class);
    }


    public Flowable<EventNotification> newEventsNotifications() {
        Map<String, Object> eventFilter = new HashMap<>();
        eventFilter.put("from_block", 0);
        return web3jService.subscribe(
                new Request<>(
                        "starcoin_subscribe",
                        Arrays.asList(Kind.Events, eventFilter),
                        web3jService,
                        EthSubscribe.class),
                "starcoin_unsubscribe",
                EventNotification.class);
    }



}
