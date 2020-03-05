package com.iris.java.onlinejudge.judger.core;

import com.iris.java.onlinejudge.judger.Execute.Executor;
import com.iris.java.onlinejudge.judger.application.ApplicationNotifier;
import com.iris.java.onlinejudge.judger.pojo.bean.*;
import com.iris.java.onlinejudge.judger.utils.Enums.JudgeResultTag;
import com.iris.java.onlinejudge.judger.utils.FileCompareUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
public class Runner {

    @Autowired
    Executor executor;

    @Autowired
    ApplicationNotifier applicationNotifier;

    @Autowired
    FileCompareUtil fileCompareUtil;

    /**
     *
     * 评测主函数，跑遍所有TestCase
     *
     * @param task
     */
    public void runProgramWholeTask(Task task){

        ResultTask resultTask = new ResultTask();
        resultTask.setExcuteStartTime(new Date());
        resultTask.setResultScore(0);
        resultTask.setResultUsedMemory(new Long(0));
        resultTask.setResultUsedTime(new Long(0));

        // 对 Task 里的每个 taskCase
        int i = 0;
        for(TaskCase taskCase : task){

            String runningCommand = taskCase.getRunningCommand();
            String inputFilePath = taskCase.getInputFilePath();
            String outputFilePath = taskCase.getOutputFilePath();
            Integer timeLimit = this.JUDGE_TIME_LIMIT;
            if(task.getLanguage().getLangurageName().equals("Java")){ // Java 运行时间限制 * 2
                timeLimit *= 2;
            }
            Integer memoryLimit = this.JUDGE_MEMORY_LIMIT;

            ExecuteResult executeResultCase = null;
            // TODO: 处理Case运行异常
            try {
                String realCommand = runningCommand.replace(" ","@");
                executeResultCase = executor.judgeExecute(realCommand,timeLimit,memoryLimit,inputFilePath,outputFilePath);
            } catch (Exception e) {
                e.printStackTrace();
            }

            ResultTaskCase resultTaskCase = parseCaseResultFromExecution(executeResultCase,i,taskCase);

            // notify: 单个Case跑完了
            applicationNotifier.onOneCaseFinished(resultTaskCase,task.getSubmissionId());

            // 如果此Case不对，直接不用往下测了
            if(!resultTaskCase.getStatus().equals(JudgeResultTag.AC.value)){
                resultTask.setResultStatus(resultTaskCase.getStatus());
                resultTask.setErrorMsg(resultTaskCase.getErrorMessage()); //记录错误信息

                // notify：整个 Task 结束了
                applicationNotifier.onTaskFinished(resultTask,task.getSubmissionId());

                return ;
            }
            else{ // 如果此Case正确，更新 resultTask，继续循环
                resultTask.setResultUsedMemory(Math.max(resultTask.getResultUsedMemory(),resultTaskCase.getMemoryUsed()));
                resultTask.setResultUsedTime(Math.max(resultTask.getResultUsedTime(),resultTaskCase.getTimeUsed()));
                resultTask.setResultScore(resultTask.getResultScore() + taskCase.getScore());
            }
        }

        resultTask.setResultStatus(JudgeResultTag.AC.value);
        // notify：整个 Task 结束了
        applicationNotifier.onTaskFinished(resultTask,task.getSubmissionId());

    }

    private ResultTaskCase parseCaseResultFromExecution(ExecuteResult executeResultCase,Integer caseId,TaskCase taskCase) {

        Integer status = -1;

        ResultTaskCase resultTaskCase = new ResultTaskCase();

        // executor 中的 AC不一定是真的AC（虽然正常运行，但输出内容可能不对）
        if(executeResultCase.getStatus().equals(JudgeResultTag.AC.value)){
            // 判断： AC ？ WA ？ PE ？
            status = fileCompareUtil.compare(taskCase);
        }
        else{
            // MLE、RE、TLE 是直接由 Executor 判断出来的
            status = executeResultCase.getStatus();
        }
        resultTaskCase.setStatus(status);
        resultTaskCase.setTimeUsed(executeResultCase.getTimeUsed());
        resultTaskCase.setMemoryUsed(executeResultCase.getMemoryUsed());
        resultTaskCase.setCaseId(caseId);
        resultTaskCase.setErrorMessage(executeResultCase.getErrorMsg());

        return resultTaskCase;

    }


    @Value("${juegecode.timeLimit}")
    Integer JUDGE_TIME_LIMIT;

    @Value("${judgecode.memoryLimit}")
    Integer JUDGE_MEMORY_LIMIT;

}
