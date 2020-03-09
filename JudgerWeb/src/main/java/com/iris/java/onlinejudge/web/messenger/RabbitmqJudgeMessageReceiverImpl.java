package com.iris.java.onlinejudge.web.messenger;

import com.alibaba.fastjson.JSON;
import com.iris.java.onlinejudge.web.config.RabbitmqConfig;
import com.iris.java.onlinejudge.web.mapper.normal.SubmissionResultMapper;
import com.iris.java.onlinejudge.web.pojo.bean.ResultTask;
import com.iris.java.onlinejudge.web.pojo.db.SubmissionResult;
import com.iris.java.onlinejudge.web.pojo.message.SubmissionNotifyMessage;
import com.iris.java.onlinejudge.web.service.MessageHandleService;
import com.iris.java.onlinejudge.web.utils.Enums.EventTag;
import com.iris.java.onlinejudge.web.utils.Enums.JudgeResultTag;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class RabbitmqJudgeMessageReceiverImpl implements JudgerMessageReceiver {

    @Autowired
    MessageHandleService messageHandleService;

    @Override
    @RabbitListener(queues = RabbitmqConfig.JUDGE_RESULT_QUEUE)
    public void JudgeResultReceive(String submissionResultJSON) {

        try{
            SubmissionNotifyMessage submissionNotifyMessage = JSON.parseObject(submissionResultJSON,SubmissionNotifyMessage.class);

            System.out.printf("receive judge message of submission %s : %s\n",submissionNotifyMessage.getSubmissionId(),EventTag.getIndex(submissionNotifyMessage.getEventId()));

            messageHandleService.switchMessage(submissionNotifyMessage);

        }catch(Exception e){
            /**
             * 消息处理过程中产生了任何异常，都要catch住
             *（若异常直接抛出，消息会一直重复消费下去）
             */
            e.printStackTrace();

            // TODO：抛出全局异常。（使用全局异常拦截器）
        }

    }

    @Override
    @RabbitListener(queues = RabbitmqConfig.HEARTBEAT_QUEUE)
    public void HeartbeatReceive(String heartbeatJSON) {
        System.out.printf("receive heartbeat : %s\n",heartbeatJSON);
    }
}
