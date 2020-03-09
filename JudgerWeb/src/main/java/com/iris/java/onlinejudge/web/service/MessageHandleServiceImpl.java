package com.iris.java.onlinejudge.web.service;

import com.iris.java.onlinejudge.web.controller.websocket.SubmitIdSubscribeServer;
import com.iris.java.onlinejudge.web.mapper.normal.SubmissionResultMapper;
import com.iris.java.onlinejudge.web.pojo.bean.ResultTask;
import com.iris.java.onlinejudge.web.pojo.db.Submission;
import com.iris.java.onlinejudge.web.pojo.db.SubmissionResult;
import com.iris.java.onlinejudge.web.pojo.message.SubmissionNotifyMessage;
import com.iris.java.onlinejudge.web.redis.PrefixKeys.SubmissionResultKey;
import com.iris.java.onlinejudge.web.redis.RedisService;
import com.iris.java.onlinejudge.web.utils.Enums.EventTag;
import com.iris.java.onlinejudge.web.utils.Enums.JudgeResultTag;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MessageHandleServiceImpl implements MessageHandleService{


    @Autowired
    RedisService redisService;

    @Autowired
    SubmissionResultService submissionResultService;

    @Autowired
    SubmitIdSubscribeServer submitIdSubscribeServer;

    @Override
    public void switchMessage(SubmissionNotifyMessage submissionNotifyMessage){

        Integer event = submissionNotifyMessage.getEventId();
        /**
         *
         * 处理流程都是:
         *
         * 首先,更新此 submissionId 数据到缓存(Redis)
         *
         * 如果是结束态(Task结束/编译错误/系统错误)，持久化到数据库
         *
         */

        // 首先，都是要写缓存
        redisService.set(SubmissionResultKey.getById,submissionNotifyMessage.getSubmissionId(),submissionNotifyMessage);

        if(event.equals(EventTag.SubmissionCreated.value)){
            SubmissionCreated(submissionNotifyMessage);
        }
        else if(event.equals(EventTag.CompileStart.value)){
            CompileStart(submissionNotifyMessage);
        }
        else if(event.equals(EventTag.CompileSucceed.value)){
            CompileSucceed(submissionNotifyMessage);
        }
        else if(event.equals(EventTag.CompileFailed.value)){
            CompileFailed(submissionNotifyMessage);
        }
        else if(event.equals(EventTag.OneCaseFinished.value)){
            OneCaseFinished(submissionNotifyMessage);
        }
        else if(event.equals(EventTag.TaskFinished.value)){
            TaskFinished(submissionNotifyMessage);
        }
        else if(event.equals(EventTag.SystemError.value)){
            SystemError(submissionNotifyMessage);
        }


        submitIdSubscribeServer.sendJudgeResultBySubmissionId(submissionNotifyMessage);


    }

    @Override
    public void SubmissionCreated(SubmissionNotifyMessage submissionNotifyMessage) {
    }

    @Override
    public void CompileStart(SubmissionNotifyMessage submissionNotifyMessage) {

    }

    @Override
    public void CompileSucceed(SubmissionNotifyMessage submissionNotifyMessage) {

    }

    // 结束态
    @Override
    public void CompileFailed(SubmissionNotifyMessage submissionNotifyMessage) {

        // 持久化到数据库
        SubmissionResult submissionResult = new SubmissionResult();
        submissionResult.setSubmissionId(submissionNotifyMessage.getSubmissionId());
        submissionResult.setResultStatus(JudgeResultTag.CE.value);
        submissionResultService.insertOneResult(submissionResult);
    }

    @Override
    public void OneCaseFinished(SubmissionNotifyMessage submissionNotifyMessage) {

    }

    // 结束态
    @Override
    public void TaskFinished(SubmissionNotifyMessage submissionNotifyMessage) {

        // 持久化到数据库
        ResultTask thisResult = submissionNotifyMessage.getData();
        SubmissionResult submissionResult = new SubmissionResult();
        BeanUtils.copyProperties(thisResult,submissionResult);
        submissionResult.setSubmissionId(submissionNotifyMessage.getSubmissionId());

        submissionResultService.insertOneResult(submissionResult);

    }

    // 结束态
    @Override
    public void SystemError(SubmissionNotifyMessage submissionNotifyMessage) {

        // 持久化到数据库
        SubmissionResult submissionResult = new SubmissionResult();
        submissionResult.setSubmissionId(submissionNotifyMessage.getSubmissionId());
        submissionResult.setResultStatus(JudgeResultTag.SE.value);
        submissionResultService.insertOneResult(submissionResult);
    }

}
