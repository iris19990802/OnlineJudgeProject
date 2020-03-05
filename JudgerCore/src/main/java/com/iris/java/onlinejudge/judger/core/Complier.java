package com.iris.java.onlinejudge.judger.core;


import com.iris.java.onlinejudge.judger.Execute.Executor;
import com.iris.java.onlinejudge.judger.application.ApplicationNotifier;
import com.iris.java.onlinejudge.judger.pojo.bean.*;
import com.iris.java.onlinejudge.judger.utils.Enums.JudgeResultTag;
import com.iris.java.onlinejudge.judger.utils.MyFileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Complier {


    @Autowired
    MyFileUtils myFileUtils;

    @Autowired
    ApplicationNotifier applicationNotifier;

    @Autowired
    Executor executor;

    public boolean compile(Task task){

        applicationNotifier.onCompileStart(task.getSubmissionId());
        ExecuteResult compileResult =  getCompileResult(task);

        // 用于封装编译结果
        ResultTask resultTask = new ResultTask();
        if(compileResult.isError()){// 如果编译失败，返回false，封装失败信息，并直接通知Task结束
            resultTask.setErrorMsg(compileResult.getErrorMsg());
            resultTask.setResultStatus(JudgeResultTag.CE.value);
        }
        else{
            resultTask.setResultStatus(JudgeResultTag.PD.value); // 如果编译成功，则继续往下，此Case暂时还没完（Pending）

        }

        // notify
        applicationNotifier.onCompileFinished(resultTask,task.getSubmissionId());

        return (!compileResult.isError());

    }

    // 暂时没有收集编译信息，只按照"普通的命令"来处理
    private ExecuteResult getCompileResult(Task task) {
        String compileCommand = generateCompileCommand(task);

        //String compileLogOutputFilePath = task.getCompileLogFilePath();

        String finalCompileCommand = compileCommand.replace(" ","@");

        ExecuteResult executeResult = null;
        try {
            executeResult = executor.normalExecute(finalCompileCommand);
        }catch(Exception e){ // TODO: executor 出错处理
            e.printStackTrace();
        }

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
