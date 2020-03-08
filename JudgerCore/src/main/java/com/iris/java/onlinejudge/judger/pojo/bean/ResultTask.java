package com.iris.java.onlinejudge.judger.pojo.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ResultTask {


    private Long resultUsedTime;

    private Long resultUsedMemory;

    private Integer resultStatus;

    private Integer resultScore;

    private Date excuteStartTime;

    private String errorMsg;

    private Integer currentTaskCase;

    private List<ResultTaskCase> resultTaskCaseList;

    public ResultTask(){
        resultTaskCaseList = new ArrayList<>();
    }


    public void addResultCase(ResultTaskCase resultTaskCase){
        this.resultTaskCaseList.add(resultTaskCase);
    }

    public Integer getCurrentTaskCase() {
        return currentTaskCase;
    }

    public void setCurrentTaskCase(Integer currentTaskCase) {
        this.currentTaskCase = currentTaskCase;
    }

    public Long getResultUsedTime() {
        return resultUsedTime;
    }

    public void setResultUsedTime(Long resultUsedTime) {
        this.resultUsedTime = resultUsedTime;
    }

    public Long getResultUsedMemory() {
        return resultUsedMemory;
    }

    public void setResultUsedMemory(Long resultUsedMemory) {
        this.resultUsedMemory = resultUsedMemory;
    }

    public Integer getResultStatus() {
        return resultStatus;
    }

    public void setResultStatus(Integer resultStatus) {
        this.resultStatus = resultStatus;
    }

    public Integer getResultScore() {
        return resultScore;
    }

    public void setResultScore(Integer resultScore) {
        this.resultScore = resultScore;
    }

    public Date getExcuteStartTime() {
        return excuteStartTime;
    }

    public void setExcuteStartTime(Date excuteStartTime) {
        this.excuteStartTime = excuteStartTime;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public List<ResultTaskCase> getResultTaskCaseList() {
        return resultTaskCaseList;
    }

    public void setResultTaskCaseList(List<ResultTaskCase> resultTaskCaseList) {
        this.resultTaskCaseList = resultTaskCaseList;
    }
}
