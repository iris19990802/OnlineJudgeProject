package com.iris.java.onlinejudge.web.messenger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.iris.java.onlinejudge.web.config.RabbitmqConfig;
import com.iris.java.onlinejudge.web.messenger.JudgerMessageSender;
import com.iris.java.onlinejudge.web.pojo.bo.SubmissionBO;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class RabbitmqJudgeMessageSenderImpl implements JudgerMessageSender {

    @Autowired
    AmqpTemplate amqpTemplate;

    @Override
    public void submmitMessageSender(SubmissionBO submissionBO) {

        //submissionBO,SerializerFeature.WriteMapNullValue
        String submissionJSON = JSON.toJSON(submissionBO).toString();
        amqpTemplate.convertAndSend(RabbitmqConfig.SUBMIT_QUERY_QUEUE,submissionJSON);

        System.out.printf("successfully send submission message : %s\n",submissionJSON);
    }

}
