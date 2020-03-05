package com.iris.java.onlinejudge.judger.pojo.bean;


import org.apache.commons.lang3.StringUtils;

public class ExecuteResult {

    Integer status;

    Long timeUsed;

    Long memoryUsed;

    String errorMsg;

    /**
     * 判断命令行运行是否出错
     *（errorMsg为空字符串或NULL，则说明没出错）
     * @return
     */
    public boolean isError(){
        return ( !StringUtils.isBlank(errorMsg) );
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public ExecuteResult(Integer status, Long timeUsed, Long memoryUsed, String errorMsg) {
        this.status = status;
        this.timeUsed = timeUsed;
        this.memoryUsed = memoryUsed;
        this.errorMsg = errorMsg;
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
}
