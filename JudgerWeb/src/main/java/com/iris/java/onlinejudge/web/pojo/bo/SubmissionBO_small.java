package com.iris.java.onlinejudge.web.pojo.bo;


// lombok 库的使用（免去Getter、Setter、构造函数）


import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;


public class SubmissionBO_small implements Serializable {

    @NotNull
    private String problemId;


    private String userCode;

    @NotNull
    private Integer languageId;


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

}
