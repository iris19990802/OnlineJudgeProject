package com.iris.java.onlinejudge.web.messenger;

import com.alibaba.fastjson.JSON;
import com.iris.java.onlinejudge.web.config.RabbitmqConfig;
import com.iris.java.onlinejudge.web.pojo.message.SubmissionNotifyMessage;
import com.iris.java.onlinejudge.web.utils.Enums.EventTag;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Component
public class RabbitmqJudgeMessageReceiverImpl implements JudgerMessageReceiver {

    @Override
    @RabbitListener(queues = RabbitmqConfig.JUDGE_RESULT_QUEUE)
    public void JudgeResultReceive(String submissionResultJSON) {

        SubmissionNotifyMessage submissionNotifyMessage = JSON.parseObject(submissionResultJSON,SubmissionNotifyMessage.class);

        System.out.printf("receive judge message of submission %s : %s\n",submissionNotifyMessage.getSubmissionId(),EventTag.getIndex(submissionNotifyMessage.getEventId()));

    }

    @Override
    @RabbitListener(queues = RabbitmqConfig.HEARTBEAT_QUEUE)
    public void HeartbeatReceive(String heartbeatJSON) {
        System.out.printf("receive heartbeat : %s\n",heartbeatJSON);
    }
}
