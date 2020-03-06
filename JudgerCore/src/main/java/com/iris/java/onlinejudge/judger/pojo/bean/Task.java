package com.iris.java.onlinejudge.judger.pojo.bean;


import com.iris.java.onlinejudge.judger.pojo.db.Language;
import com.iris.java.onlinejudge.judger.pojo.db.ProblemCase;
import com.iris.java.onlinejudge.judger.pojo.db.Problems;

import java.util.Iterator;
import java.util.List;



/*
     实现自定义迭代器：Task 迭代生成 TaskCase

     （ implements Iterable<TaskCase> ）

*/
public class Task implements Iterable<TaskCase>{

    private String submissionId;

    private String workDir; // 工作目录，按 "提交id" 分类

    private String userCodeFileName;

    private String checkpointFileDir; // 测试点Case 的 input、output文件所在目录

    private Language language;

    private Problems problems;

    private List<ProblemCase> problemCases;

    public Task(String submissionId,String workDir,String userCodeFileName,String checkpointFileDir,Language language,Problems problems,List<ProblemCase> problemCases){
        this.submissionId = submissionId;
        this.workDir = workDir;
        this.userCodeFileName = userCodeFileName;
        this.checkpointFileDir = checkpointFileDir;
        this.language = language;
        this.problems = problems;
        this.problemCases = problemCases;
    }


    /**
     *  自定义迭代器的实现（Task 迭代生成 TaskCase）
     *  参考：https://www.jianshu.com/p/cf82ab7e51ef
     */
    @Override
    public Iterator<TaskCase> iterator() {

        return new Iterator<TaskCase>() {
            private int index = 0;
            @Override
            public boolean hasNext() {
                return index < problemCases.size();
            }

            @Override
            public TaskCase next() {
                TaskCase thisCase = getOneTaskCase(index);
                index += 1;
                return thisCase;
            }
        };
    }


    /**
     * 得到第i个TaskCase
     * @param caseId
     * @return
     */
    public TaskCase getOneTaskCase(int caseId) {

        // 用户提交代码的路径（不带语言后缀名）
        String userFileLocation = String.format("%s/%s",new Object[] {workDir, userCodeFileName});

        String compileCommand = language.getLanguageCompileCommand().replace("{filename}",userFileLocation);
        //String runningCommand = language.getLanguageRunCommand().replace("{filename}",userFileLocation);
        String runningCommand = language.getLanguageRunCommand().replace("{filedir}",workDir);
        runningCommand = runningCommand.replace("{filename}",userCodeFileName);

        String inputFilePath = String.format("%s/input#%s.txt",
                new Object[] { checkpointFileDir, caseId}); // checkpoint in 文件的位置

        String outputFilePath =  String.format("%s/output#%s.txt",
                new Object[] {workDir, caseId});// 用户代码运行所得输出结果的文件位置

        String stdOutputFilePath = String.format("%s/output#%s.txt",
                new Object[] { checkpointFileDir, caseId }); // checkpoint out 文件的位置

        Integer score = problemCases.get(caseId).getCheckpointScore();

        return new TaskCase(compileCommand, runningCommand, inputFilePath, outputFilePath, stdOutputFilePath,score);


    }

    /**
     * 得到编译日志所在路径（以"工作目录为基准生成"）（后缀名为.log）
     * @return
     */
    public String getCompileLogFilePath(){

        String compileLogFilePath = String.format("%s/%s-compile.log",
                new Object[] {this.workDir, this.userCodeFileName});// 不要字符串拼接，用format，更容易读

        return compileLogFilePath;
    }

    /**
     * 返回用户提交的代码所存储的"完整路径"（以"工作目录为基准生成"）(带语言后缀名".java")
     * @return
     */
    public String getUserCodeFileSavePathComplete(){
        String codeFilePath = String.format("%s/%s.%s",
                new Object[] {workDir, userCodeFileName, language.getCodeFileSuffix()});
        return codeFilePath;
    }

    /**
     * 返回用户提交的代码所存储的"完整路径"（不带后缀）
     * @return
     */
    public String getUserCodeFileSavePathNoSuffix(){
        String codeFilePath = String.format("%s/%s",
                new Object[] {workDir, userCodeFileName});
        return codeFilePath;
    }



    public String getSubmissionId() {
        return submissionId;
    }

    public String getWorkDir() {
        return workDir;
    }

    public String getUserCodeFileName() {
        return userCodeFileName;
    }


    public Language getLanguage() {
        return language;
    }

    public Problems getProblems() {
        return problems;
    }

    public List<ProblemCase> getProblemCases() {
        return problemCases;
    }


    public String getCheckpointFileDir() {
        return checkpointFileDir;
    }





}
