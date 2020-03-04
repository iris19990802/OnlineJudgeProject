package com.iris.java.onlinejudge.judger.core;


import com.iris.java.onlinejudge.judger.pojo.bean.Task;
import com.iris.java.onlinejudge.judger.pojo.bean.TaskFactory;
import com.iris.java.onlinejudge.judger.pojo.bo.SubmissionBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Dispatcher {

    @Autowired
    Preprocessor preprocessor;

    @Autowired
    TaskFactory taskFactory;

    // 接到 submission 请求，构建 Task
    public void handleNewSubmission(SubmissionBO submissionBO) throws Exception{

        Task newTask = taskFactory.getNewTaskFromSubmissionBO(submissionBO);

        preprocessor.preprocess(newTask,submissionBO.getUserCode());



    }



}
