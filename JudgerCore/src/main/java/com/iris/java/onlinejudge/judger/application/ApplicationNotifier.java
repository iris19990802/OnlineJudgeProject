package com.iris.java.onlinejudge.judger.application;


import com.iris.java.onlinejudge.judger.mapper.normal.SubmissionResultMapper;
import com.iris.java.onlinejudge.judger.messenger.MessageSender;
import com.iris.java.onlinejudge.judger.pojo.db.SubmissionResult;
import com.iris.java.onlinejudge.judger.pojo.bean.*;
import com.iris.java.onlinejudge.judger.pojo.message.SubmissionNotifyMessage;
import com.iris.java.onlinejudge.judger.utils.Enums.EventTag;
import com.iris.java.onlinejudge.judger.utils.Enums.JudgeResultTag;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ApplicationNotifier {

    @Autowired
    SubmissionResultMapper submissionResultMapper;

    @Autowired
    MessageSender messageSender;

    /**
     * 结束等待、开始构建Task时触发（显示：start...）
     * @param submissionId
     */
    public void onSubmissionCreated(String submissionId){

        SubmissionNotifyMessage<String> message = SubmissionNotifyMessage.normal(
                EventTag.SubmissionCreated.value,submissionId,JudgeResultTag.PD.value,"start handling ...");

        messageSender.judgeResultSender(message);
    }
    /**
     * 编译开始时触发(显示：Compling...)
     */
    public void onCompileStart(String submissionId){

        SubmissionNotifyMessage<String> message = SubmissionNotifyMessage.normal(
                EventTag.CompileStart.value,submissionId,JudgeResultTag.PD.value,"Compling ...");

        messageSender.judgeResultSender(message);
    }

    /**
     * 编译完成时触发(显示：running on task1 ...)
     * 编译成功？编译失败？(看返回的resultTask里的status值)
     * @param resultTask
     */
    public void onCompileFinished(ResultTask resultTask,String submissionId){

        SubmissionNotifyMessage<ResultTask> message = SubmissionNotifyMessage.normal(
                EventTag.CompileFinished.value,submissionId,resultTask.getResultStatus(),resultTask);

        messageSender.judgeResultSender(message);
    }


    /**
     * 当一个Case评测完时触发 (显示：running on task[i+1] ...,并返回测试结果)
     *（对于每个Case的运行结果：不存储，不做持久化，只塞入消息队列返回给用户即时观看）
     * @param resultCase
     */
    public void onOneCaseFinished(ResultTaskCase resultCase,String submissionId){

        SubmissionNotifyMessage<ResultTaskCase> message = SubmissionNotifyMessage.normal(
                EventTag.OneCaseFinished.value,submissionId,JudgeResultTag.PD.value,resultCase);

        messageSender.judgeResultSender(message);
    }

    /**
     * 整个测评结束时触发
     *（可能是完美跑完了所有Case，也可能是因为中途有Case不对了直接返回）
     * (显示：AC/WA/PE... ，并返回测评结果)
     * @param resultTask
     */
    public void onTaskFinished(ResultTask resultTask,String submissionId){

        SubmissionResult submissionResult = new SubmissionResult();
        BeanUtils.copyProperties(resultTask,submissionResult);
        submissionResult.setSubmissionId(submissionId);
        // 持久化入数据库
        //submissionResultMapper.insert(submissionResult);

        SubmissionNotifyMessage<ResultTask> message = SubmissionNotifyMessage.normal(
                EventTag.TaskFinished.value,submissionId,resultTask.getResultStatus(),resultTask);

        messageSender.judgeResultSender(message);

    }


}
