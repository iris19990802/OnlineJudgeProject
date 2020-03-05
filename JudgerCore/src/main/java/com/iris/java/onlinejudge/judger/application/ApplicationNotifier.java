package com.iris.java.onlinejudge.judger.application;


import com.iris.java.onlinejudge.judger.mapper.normal.SubmissionResultMapper;
import com.iris.java.onlinejudge.judger.messenger.MessageSender;
import com.iris.java.onlinejudge.judger.pojo.SubmissionResult;
import com.iris.java.onlinejudge.judger.pojo.bean.*;
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
        NotifyMessage<String> message = NotifyMessage.normal(
                EventTag.SubmissionCreated.value,submissionId,JudgeResultTag.PD.value,"start handling ...");
    }
    /**
     * 编译开始时触发(显示：Compling...)
     */
    public void onCompileStart(String submissionId){
        NotifyMessage<String> message = NotifyMessage.normal(
                EventTag.CompileStart.value,submissionId,JudgeResultTag.PD.value,"Compling ...");
    }

    /**
     * 编译完成时触发(显示：running on task1 ...)
     * 编译成功？编译失败？(看返回的resultTask里的status值)
     * @param resultTask
     */
    public void onCompileFinished(ResultTask resultTask,String submissionId){
        NotifyMessage<ResultTask> message = NotifyMessage.normal(
                EventTag.CompileFinished.value,submissionId,resultTask.getResultStatus(),resultTask);
    }


    /**
     * 当一个Case评测完时触发 (显示：running on task[i+1] ...,并返回测试结果)
     *（对于每个Case的运行结果：不存储，不做持久化，只塞入消息队列返回给用户即时观看）
     * @param resultCase
     */
    public void onOneCaseFinished(ResultTaskCase resultCase,String submissionId){

        NotifyMessage<ResultTaskCase> message = NotifyMessage.normal(
                EventTag.OneCaseFinished.value,submissionId,JudgeResultTag.PD.value,resultCase);
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
        submissionResultMapper.insert(submissionResult);

        NotifyMessage<ResultTask> message = NotifyMessage.normal(
                EventTag.TaskFinished.value,submissionId,resultTask.getResultStatus(),resultTask);

    }


}
