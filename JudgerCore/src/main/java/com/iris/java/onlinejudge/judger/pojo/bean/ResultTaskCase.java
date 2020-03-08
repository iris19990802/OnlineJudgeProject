package com.iris.java.onlinejudge.judger.pojo.bean;

import org.springframework.stereotype.Component;


/**
 * 正常运行生成的 ResultCase
 *
 *（直接作为返回给消息队列的data）
 *
 */
@Component
public class ResultTaskCase {

    private Integer caseId;

    private Integer status; // 枚举类（AC/WA/PE...）

    private Long timeUsed;

    private Long memoryUsed;

    private String errorMessage; // 如果是 RuntimeError ， 可能会有 erroeMessage

    public ResultTaskCase() {
    }

    public ResultTaskCase(Integer status, Long timeUsed, Long memoryUsed, String errorMessage) {
        this.status = status;
        this.timeUsed = timeUsed;
        this.memoryUsed = memoryUsed;
        this.errorMessage = errorMessage;
    }


    public Integer getCaseId() {
        return caseId;
    }

    public void setCaseId(Integer caseId) {
        this.caseId = caseId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getTimeUsed() {
        return timeUsed;
    }

    public void setTimeUsed(Long timeUsed) {
        this.timeUsed = timeUsed;
    }

    public Long getMemoryUsed() {
        return memoryUsed;
    }

    public void setMemoryUsed(Long memoryUsed) {
        this.memoryUsed = memoryUsed;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
