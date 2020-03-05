package com.iris.java.onlinejudge.judger.Execute;

import com.iris.java.onlinejudge.judger.pojo.bean.ExecuteResult;

import java.io.IOException;

public interface Executor {

    public ExecuteResult judgeExecute(String cmd, int timeLimit, int memoryLimit, String inputFile, String outputFile) throws Exception;

    public ExecuteResult normalExecute(String cmd) throws IOException;


}
