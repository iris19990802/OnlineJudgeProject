package com.iris.java.onlinejudge.judger.core;


import com.iris.java.onlinejudge.judger.application.ApplicationNotifier;
import com.iris.java.onlinejudge.judger.pojo.bean.ExecuteResult;
import com.iris.java.onlinejudge.judger.pojo.bean.Task;
import com.iris.java.onlinejudge.judger.utils.MyFileUtils;
import com.iris.java.onlinejudge.judger.utils.TaskFactory;
import com.iris.java.onlinejudge.judger.pojo.bo.SubmissionBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;

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

    @Autowired
    ApplicationNotifier applicationNotifier;

    @Autowired
    MyFileUtils myFileUtils;

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
        applicationNotifier.onSubmissionCreated(newTask.getSubmissionId());

        // preprocess
        preprocessor.preprocess(newTask,submissionBO.getUserCode());

        // compile
        if(complier.compile(newTask)){
            // run
            runner.runProgramWholeTask(newTask);
        }

        // 清理临时目录
        cleanDir(newTask);

    }

    /**
     * 清理临时目录
     *（删除 ${workdir}/${submissionId}、${workdir}/testpoints/${problemId} 下所有的文件）
     * @param newTask
     */
    // TODO: 并发与同步问题（目录、文件为共享资源）
    private void cleanDir(Task newTask) {
        String path1 = String.format("%s/%s" ,newTask.getWorkDir(),newTask.getSubmissionId());
        File dir1 = new File(path1);
        if(dir1.exists()){
            try{
                myFileUtils.deleteDirectory(dir1);
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        String path2 = String.format("%s/testpoints/%s" ,newTask.getWorkDir(),newTask.getProblems().getProblemId());
        File dir2 = new File(path1);
        if(dir2.exists()){
            try{
                myFileUtils.deleteDirectory(dir2);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }


}
