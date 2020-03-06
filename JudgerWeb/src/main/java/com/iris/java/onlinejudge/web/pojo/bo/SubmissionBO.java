package com.iris.java.onlinejudge.web.pojo.bo;


// lombok 库的使用（免去Getter、Setter、构造函数）


import java.io.Serializable;
import java.util.Date;


public class SubmissionBO implements Serializable {

    private String submissionId;

    private String problemId;

    private String userCode;

    private Integer languageId;

    private Date submitDate;


    public String getSubmissionId() {
        return submissionId;
    }

    public void setSubmissionId(String submissionId) {
        this.submissionId = submissionId;
    }

    public String getProblemId() {
        return problemId;
    }

    public void setProblemId(String problemId) {
        this.problemId = problemId;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public Integer getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Integer languageId) {
        this.languageId = languageId;
    }

    public Date getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(Date submitDate) {
        this.submitDate = submitDate;
    }
}
