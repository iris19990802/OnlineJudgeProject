package com.iris.java.onlinejudge.judger.core;


import com.iris.java.onlinejudge.judger.pojo.bean.Task;
import com.iris.java.onlinejudge.judger.utils.TaskFactory;
import com.iris.java.onlinejudge.judger.pojo.bo.SubmissionBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {

    @Autowired
    TaskFactory taskFactory;

    @Autowired
    Preprocessor preprocessor;

    @Autowired
    Complier complier;

    @Autowired
    Runner runner;

    /**
     * 接到新的 submission 请求, 开始走评测流程
     *
     * preprocess -> compile -> running & juding -> cleanWorkTempFile
     *
     * @param submissionBO
     * @throws Exception
     */
    public void handleNewSubmission(SubmissionBO submissionBO) throws Exception{

        // 构建 Task
        Task newTask = taskFactory.getNewTaskFromSubmissionBO(submissionBO);

        // preprocess
        preprocessor.preprocess(newTask,submissionBO.getUserCode());

        // compile
        complier.compile(newTask);

        // run
        runner.runProgramWholeTask(newTask);


    }



}
