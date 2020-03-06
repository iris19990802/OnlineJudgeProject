package com.iris.java.onlinejudge.judger.core;

import com.iris.java.onlinejudge.judger.application.ApplicationNotifier;
import com.iris.java.onlinejudge.judger.pojo.db.ProblemCase;
import com.iris.java.onlinejudge.judger.pojo.bean.Task;
import com.iris.java.onlinejudge.judger.pojo.bean.TaskCase;
import com.iris.java.onlinejudge.judger.utils.MyFileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class Preprocessor {

    @Autowired
    ApplicationNotifier applicationNotifier;

    @Autowired
    MyFileUtils myFileUtils;


    public void preprocess(Task task,String userCode){


        String userCodeSavePath  = task.getUserCodeFileSavePathComplete();

        try {
            // 创建 userCode 文件（用户提交的代码）
            String userCodePath = task.getUserCodeFileSavePathComplete();
            myFileUtils.createNewFileRecursive(userCodePath,userCode);

            // 创建 default 文件 TODO：这里应该被优化掉
            myFileUtils.createNewFileRecursive(DEFAULT_INPUT_FILE,"");
            myFileUtils.createNewFileRecursive(DEFAULT_OUTPUT_FILE,"");

            // 对所有的Cases，创建input、output文件
            generateInputOutputForAllCases(task);

        }catch (Exception e){
            e.printStackTrace();

            // TODO:applicationNotifier.onErrorOccurred(task.getSubmissionId());
        }

    }



    /**
     * 对所有的Cases，创建input、output文件（在 checkpoint_dir 下）
     * @param task
     */
    public void generateInputOutputForAllCases(Task task) throws Exception {

        // 遍历此问题的所有Cases

        List<ProblemCase> problemCases = task.getProblemCases();

        int i = 0;
        for(TaskCase taskCase: task){

            ProblemCase thisProblem = problemCases.get(i);

            // 创建 input 文件
            myFileUtils.createNewFileRecursive(taskCase.getInputFilePath(), thisProblem.getCheckpointInput());

            // 创建 standard Output 文件
            myFileUtils.createNewFileRecursive(taskCase.getStdOutputFilePath(),thisProblem.getCheckpointOutput());

            i += 1;

        }
    }

    @Value("${commandexecutor.defaultInputFile}")
    String DEFAULT_INPUT_FILE;

    @Value("${commandexecutor.defaultOutputFile}")
    String DEFAULT_OUTPUT_FILE;

}
