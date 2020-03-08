package com.iris.java.onlinejudge.web.pojo.bo;


// lombok 库的使用（免去Getter、Setter、构造函数）


import com.iris.java.onlinejudge.web.Validator.IsLanguage;
import com.iris.java.onlinejudge.web.Validator.IsProblem;
import com.iris.java.onlinejudge.web.Validator.IsSecureCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;


public class SubmissionBO_small implements Serializable {

    @NotNull
    @IsProblem
    private String problemId;

    @NotNull
    @IsSecureCode
    private String userCode;

    @NotNull
    @IsLanguage
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
