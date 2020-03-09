package com.iris.java.onlinejudge.judger.messenger;

// Kafka: 把数据作为json对象传递

import com.alibaba.fastjson.JSON;
import com.iris.java.onlinejudge.judger.application.ApplicationNotifier;
import com.iris.java.onlinejudge.judger.config.RabbitmqConfig;
import com.iris.java.onlinejudge.judger.core.Scheduler;
import com.iris.java.onlinejudge.judger.pojo.bo.SubmissionBO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitmqMessageReceiverImpl implements  MessageReceiver{

    @Autowired
    Scheduler scheduler;

    @Autowired
    ApplicationNotifier applicationNotifier;

    @Override
    @RabbitListener(queues=RabbitmqConfig.SUBMIT_QUERY_QUEUE)
    public void receiveNewSubmissionMessage(String submissionJson) {

        SubmissionBO submissionBO = JSON.parseObject(submissionJson,SubmissionBO.class);

        System.out.printf("receive submit message : %s\n",submissionJson);


        try {
            // 接到新请求，开始处理
            scheduler.handleNewSubmission(submissionBO);

        }catch(Exception e){
            // submission 处理流程中抛出任何异常，都返回 SystemError
            applicationNotifier.onSystemError(submissionBO.getSubmissionId());
        }

    }
}
