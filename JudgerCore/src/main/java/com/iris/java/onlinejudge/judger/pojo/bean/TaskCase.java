package com.iris.java.onlinejudge.judger.pojo.bean;



public class TaskCase {

    String compileCommand;

    String runningCommand;

    String inputFilePath;

    String outputFilePath;

    String stdOutputFilePath;

    Integer score;

    public TaskCase() {
    }

    public TaskCase(String compileCommand, String runningCommand, String inputFilePath, String outputFilePath, String stdOutputFilePath,Integer score) {
        this.compileCommand = compileCommand;
        this.runningCommand = runningCommand;
        this.inputFilePath = inputFilePath;
        this.outputFilePath = outputFilePath;
        this.stdOutputFilePath = stdOutputFilePath;
        this.score = score;
    }

    public String getCompileCommand() {
        return compileCommand;
    }

    public void setCompileCommand(String compileCommand) {
        this.compileCommand = compileCommand;
    }

    public String getRunningCommand() {
        return runningCommand;
    }

    public void setRunningCommand(String runningCommand) {
        this.runningCommand = runningCommand;
    }

    public String getInputFilePath() {
        return inputFilePath;
    }

    public void setInputFilePath(String inputFilePath) {
        this.inputFilePath = inputFilePath;
    }

    public String getOutputFilePath() {
        return outputFilePath;
    }

    public void setOutputFilePath(String outputFilePath) {
        this.outputFilePath = outputFilePath;
    }

    public String getStdOutputFilePath() {
        return stdOutputFilePath;
    }

    public void setStdOutputFilePath(String stdOutputFilePath) {
        this.stdOutputFilePath = stdOutputFilePath;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
