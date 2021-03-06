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


/**
 * 只有TaskFinished返回 ResultTask 对象
 * 其他都返回字符串（代表提示信息）
 *
 */
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
        SubmissionNotifyMessage message = SubmissionNotifyMessage.normal(
                EventTag.SubmissionCreated.value,submissionId,JudgeResultTag.PD.value,null);

        messageSender.judgeResultSender(message);
    }
    /**
     * 编译开始时触发(显示：Compling...)
     */
    public void onCompileStart(String submissionId){

        SubmissionNotifyMessage message = SubmissionNotifyMessage.normal(
                EventTag.CompileStart.value,submissionId,JudgeResultTag.PD.value,null);

        messageSender.judgeResultSender(message);
    }

    /**
     * 编译成功时触发(显示：running on task1 ...)
     *
     */
    public void onCompileSucceed(String submissionId){

        SubmissionNotifyMessage message = SubmissionNotifyMessage.normal(
                EventTag.CompileSucceed.value,submissionId,JudgeResultTag.PD.value,null);

        messageSender.judgeResultSender(message);
    }

    /**
     * 编译失败时触发
     * @param submissionId
     */
    public void onCompileFailed(String submissionId){

        SubmissionNotifyMessage message = SubmissionNotifyMessage.normal(
                EventTag.CompileFailed.value,submissionId,JudgeResultTag.CE.value,null);

        messageSender.judgeResultSender(message);
    }


    /**
     * 当一个Case评测完时触发 (显示：running on task[i+1] ...,并返回测试结果)
     *（对于每个Case的运行结果：不存储，不做持久化，只塞入消息队列返回给用户即时观看）
     *
     * (status 是"未完成态"（Pending）)
     * @param resultTask
     */
    public void onOneCaseFinished(ResultTask resultTask,String submissionId){


        SubmissionNotifyMessage message = SubmissionNotifyMessage.normal(
                EventTag.OneCaseFinished.value,submissionId,JudgeResultTag.PD.value,resultTask);

        // 注意：返回的也是 ResultTask
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

        // task持久化工作不在 judge_core 做，统一在 web_server 做
        //submissionResultMapper.insert(submissionResult);

        SubmissionNotifyMessage message = SubmissionNotifyMessage.normal(
                EventTag.TaskFinished.value,submissionId,resultTask.getResultStatus(),resultTask);

        messageSender.judgeResultSender(message);

    }


    /**
     * 拦截全局异常
     *（输出"System error"）
     * @param submissionId
     */
    public void onSystemError(String submissionId){

        SubmissionNotifyMessage message = SubmissionNotifyMessage.normal(
                EventTag.SystemError.value,submissionId,JudgeResultTag.SE.value,null);

        messageSender.judgeResultSender(message);
    }



}
