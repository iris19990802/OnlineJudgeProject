package com.iris.java.onlinejudge.judger.core;


import com.iris.java.onlinejudge.judger.Execute.Executor;
import com.iris.java.onlinejudge.judger.application.ApplicationNotifier;
import com.iris.java.onlinejudge.judger.pojo.bean.*;
import com.iris.java.onlinejudge.judger.utils.Enums.JudgeResultTag;
import com.iris.java.onlinejudge.judger.utils.MyFileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class Complier {


    @Autowired
    MyFileUtils myFileUtils;

    @Autowired
    ApplicationNotifier applicationNotifier;

    @Autowired
    Executor executor;

    public boolean compile(Task task) throws Exception{

        applicationNotifier.onCompileStart(task.getSubmissionId());

        // 执行编译
        getCompileResult(task);

        // 由于C++难以区分编译warning和error（都走标准错误输出，哪怕warnning也会有errorMsg，且exitCode都是0）
        // 这里用"是否生成可执行文件"为标准，来判断编译是否成功

        File runningFile = new File(task.getCompileResultFilePath()); // 编译生成的文件路径（可执行文件路径）

        if(runningFile.exists()){
            applicationNotifier.onCompileSucceed(task.getSubmissionId()); // 如果编译成功，则继续往下，此Case暂时还没完（Pending）
        }else{
            applicationNotifier.onCompileFailed(task.getSubmissionId());
        }

        return runningFile.exists();

    }

    // 暂时没有收集编译信息，只按照"普通的命令"来处理
    private ExecuteResult getCompileResult(Task task) throws Exception{
        String compileCommand = generateCompileCommand(task);

        //String compileLogOutputFilePath = task.getCompileLogFilePath();

        String finalCompileCommand = compileCommand.replace(" ","@");

        ExecuteResult executeResult = null;

        executeResult = executor.normalExecute(finalCompileCommand);

        return executeResult;
    }


    private String generateCompileCommand(Task task){

        String userFileNoSuffix = task.getUserCodeFileSavePathNoSuffix();
        String compile_command_pattern = task.getLanguage().getLanguageCompileCommand();
        String finalCompileCommand = compile_command_pattern.replace("{filename}",userFileNoSuffix);

        return finalCompileCommand;
    }

    // TODO: 接收并解析"编译信息"
    private String getCompileLogOutput(String compileLogPath) throws Exception {

        String fileContent = myFileUtils.readFileContent(compileLogPath);
        return fileContent;
    }

    @Value("${judger.coreScriptLocation}")
    String JUDGE_SCRIPT_LOCATION;
}
