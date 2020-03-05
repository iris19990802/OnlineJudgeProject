package com.iris.java.onlinejudge.judger.application;


import com.iris.java.onlinejudge.judger.mapper.normal.SubmissionResultMapper;
import com.iris.java.onlinejudge.judger.pojo.SubmissionResult;
import com.iris.java.onlinejudge.judger.pojo.bean.ResultTask;
import com.iris.java.onlinejudge.judger.pojo.bean.ResultTaskCase;
import com.iris.java.onlinejudge.judger.pojo.bean.Task;
import com.iris.java.onlinejudge.judger.pojo.bean.TaskCase;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ApplicationNotifier {

    @Autowired
    SubmissionResultMapper submissionResultMapper;
    /**
     * 编译完成时触发(显示：Compling...)
     * @param task
     * @param resultTask
     */
    public void onCompileStart(Task task, ResultTask resultTask){
        ;
    }

    /**
     * 编译完成时触发(显示：running on task1 ...)
     * TODO: 编译成功？编译失败？
     * @param task
     * @param resultTask
     */
    public void onCompileFinished(Task task, ResultTask resultTask){
        ;
    }


    /**
     * 当一个Case评测完时触发 (显示：running on task[i+1] ...,并返回测试结果)
     *（对于每个Case的运行结果：不存储，不做持久化，只塞入消息队列返回给用户即时观看）
     * @param resultCase
     */
    public void onOneCaseFinished(ResultTaskCase resultCase){

        ;
    }

    /**
     * 整个测评结束时触发
     *（可能是完美跑完了所有Case，也可能是因为中途有Case不对了直接返回）
     * (显示：AC/WA/PE... ，并返回测评结果)
     * @param resultTask
     */
    public void onTaskFinished(ResultTask resultTask){

        SubmissionResult submissionResult = new SubmissionResult();
        BeanUtils.copyProperties(resultTask,submissionResult);

        // 持久化入数据库
        submissionResultMapper.insert(submissionResult);

    }


}
