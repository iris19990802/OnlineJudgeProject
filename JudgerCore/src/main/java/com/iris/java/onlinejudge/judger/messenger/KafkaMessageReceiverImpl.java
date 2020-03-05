package com.iris.java.onlinejudge.judger.messenger;

// Kafka: 把数据作为json对象传递

import org.springframework.stereotype.Component;

@Component
public class KafkaMessageReceiverImpl implements  MessageReceiver{
    @Override
    public void newSubmissionHandler() {

    }
}
