package com.iris.java.onlinejudge.web.controller.normal;


import com.iris.java.onlinejudge.web.messenger.JudgerMessageSender;
import com.iris.java.onlinejudge.web.pojo.bo.SubmissionBO;
import com.iris.java.onlinejudge.web.pojo.bo.SubmissionBO_small;
import com.iris.java.onlinejudge.web.pojo.db.Submission;
import com.iris.java.onlinejudge.web.service.SubmissionService;
import com.iris.java.onlinejudge.web.utils.JSONResult;
import com.iris.java.onlinejudge.web.utils.UUIDUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Date;
import java.util.UUID;

@Api(value="提交接口",tags={"用户提交submission的请求接口"})
@RequestMapping("/api/submission")
@RestController
public class SubmissionController {

    @Autowired
    JudgerMessageSender rabbitmqJudgeMessageSender;

    @Autowired
    SubmissionService submissionService;

    @Autowired
    UUIDUtil uuidUtil;

    @ApiOperation(value="用户提交代码",notes="用户提交代码",httpMethod = "POST")
    @PostMapping("/submit")
    public JSONResult receiveNewTask(@RequestBody @Valid SubmissionBO_small submissionBO_small ){ // 完成参数校验

        // 生成 submissionId
        String submissionId = uuidUtil.getUUID();

        SubmissionBO submissionBO = new SubmissionBO();
        BeanUtils.copyProperties(submissionBO_small,submissionBO);
        submissionBO.setSubmitDate(new Date());
        submissionBO.setSubmissionId(submissionId);

        // 把此Submission持久化到数据库
        Submission submission = new Submission();
        BeanUtils.copyProperties(submissionBO,submission);
        submissionService.insertOneSubmission(submission);

        // 把提交请求，发送到消息队列
        rabbitmqJudgeMessageSender.submmitMessageSender(submissionBO);

        // 把生成的SessionId返回给用户，用于发起websocket请求
        return JSONResult.ok(submissionId);
    }

}
