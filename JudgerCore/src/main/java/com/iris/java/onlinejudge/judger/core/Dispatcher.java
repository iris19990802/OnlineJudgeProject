package com.iris.java.onlinejudge.judger.core;


import com.iris.java.onlinejudge.judger.pojo.bean.Task;
import com.iris.java.onlinejudge.judger.utils.TaskFactory;
import com.iris.java.onlinejudge.judger.pojo.bo.SubmissionBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Dispatcher {

    @Autowired
    TaskFactory taskFactory;

    @Autowired
    Preprocessor preprocessor;

    @Autowired
    Complier complier;

    // 接到 submission 请求
    public void handleNewSubmission(SubmissionBO submissionBO) throws Exception{

        // 构建 Task
        Task newTask = taskFactory.getNewTaskFromSubmissionBO(submissionBO);

        // preprocess
        preprocessor.preprocess(newTask,submissionBO.getUserCode());

        // compile
        complier.compile(newTask);


    }



}
