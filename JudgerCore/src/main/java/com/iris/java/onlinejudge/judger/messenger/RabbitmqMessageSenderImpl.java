package com.iris.java.onlinejudge.judger.messenger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.iris.java.onlinejudge.judger.config.RabbitmqConfig;
import com.iris.java.onlinejudge.judger.pojo.message.HeartbeatNotifyMessage;
import com.iris.java.onlinejudge.judger.pojo.message.SubmissionNotifyMessage;
import com.iris.java.onlinejudge.judger.utils.Enums.EventTag;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitmqMessageSenderImpl implements MessageSender {

    @Autowired
    AmqpTemplate amqpTemplate;

    @Override
    public void judgeResultSender(SubmissionNotifyMessage message) {

        //String submissionMessageJSON = JSON.toJSONString(message,SerializerFeature.WriteMapNullValue);

        String submissionMessageJSON = JSON.toJSON(message).toString();

        amqpTemplate.convertAndSend(RabbitmqConfig.JUDGE_RESULT_QUEUE,submissionMessageJSON);

        System.out.printf("judge result send of submissionId %s : %s\n",message.getSubmissionId(),EventTag.getIndex(Integer.valueOf(message.getEventId())));

    }

    @Override
    public void heartbeatSender(HeartbeatNotifyMessage message) {
        ;
    }
}
