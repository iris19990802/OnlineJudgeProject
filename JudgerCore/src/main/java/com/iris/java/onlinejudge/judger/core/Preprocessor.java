package com.iris.java.onlinejudge.judger.core;


import com.iris.java.onlinejudge.judger.application.ApplicationNotifier;
import com.iris.java.onlinejudge.judger.pojo.ProblemCase;
import com.iris.java.onlinejudge.judger.pojo.bean.Task;
import com.iris.java.onlinejudge.judger.pojo.bean.TaskCase;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.PosixFilePermission;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class Preprocessor {

    @Autowired
    ApplicationNotifier applicationNotifier;

    public void preprocess(Task task,String userCode){

        // 创建工作目录 && 设置权限
        setWorkDirectory(task.getWorkDir());

        String userCodeSavePath  = task.getUserCodeFileSavePath();

        try {
            // 创建 userCode 文件（用户提交的代码）
            // 在 work_dir 工作目录下
            //（apache commons io库，让文件读写更便捷）
            String userCodePath = task.getUserCodeFileSavePath();
            FileOutputStream outputStream = new FileOutputStream(new File(userCodePath));
            IOUtils.write(userCode, outputStream);
            IOUtils.closeQuietly(outputStream);

            // 对所有的Cases，创建input、output文件
            generateInputOutputForAllCases(task);

        }catch (Exception e){
            e.printStackTrace();
            //applicationNotifier.onErrorOccurred(task.getSubmissionId());
        }

    }


    /**
     * 创建目录 && 设置权限
     * @param workDirectoryPath
     */
    public void setWorkDirectory(String workDirectoryPath){

        File workDirectory = new File(workDirectoryPath);
        if(!workDirectory.exists()){
            boolean flag = workDirectory.mkdirs();
            if(!flag){
                System.out.println("Failed to create directory: " +  workDirectoryPath);
            }

        }
//        if ( !workDirectory.exists() && !workDirectory.mkdirs() ) {
//            throw new CreateDirectoryException("Failed to create directory: " + workDirectory);
//        }

//        setWorkDirectoryPermission(workDirectory);
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

            {
                // 创建 input 文件
                FileOutputStream outputStream = new FileOutputStream(new File(taskCase.getInputFilePath()));
                String inputContent = thisProblem.getCheckpointInput();
                IOUtils.write(inputContent, outputStream);
                IOUtils.closeQuietly(outputStream);
            }
            {
                // 创建 standard output 文件
                FileOutputStream outputStream = new FileOutputStream(new File(taskCase.getOutputFilePath()));
                String standartOutputContent = thisProblem.getCheckpointOutput();
                IOUtils.write(standartOutputContent, outputStream);
                IOUtils.closeQuietly(outputStream);
            }
            i += 1;

        }
    }

}
