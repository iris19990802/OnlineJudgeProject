package com.iris.java.onlinejudge.web.pojo.db;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

public class Submission {
    /**
     * submission_id
     */
    @Id
    @Column(name = "submission_id")
    private String submissionId;

    /**
     * problem_id
     */
    @Column(name = "problem_id")
    private String problemId;

    /**
     * language_id
     */
    @Column(name = "language_id")
    private Integer languageId;

    /**
     * user_id
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * submit_time
     */
    @Column(name = "submit_time")
    private Date submitDate;

    /**
     * user_code
     */
    @Column(name = "user_code")
    private String userCode;

    /**
     * 获取submission_id
     *
     * @return submission_id - submission_id
     */
    public String getSubmissionId() {
        return submissionId;
    }

    /**
     * 设置submission_id
     *
     * @param submissionId submission_id
     */
    public void setSubmissionId(String submissionId) {
        this.submissionId = submissionId;
    }

    /**
     * 获取problem_id
     *
     * @return problem_id - problem_id
     */
    public String getProblemId() {
        return problemId;
    }

    /**
     * 设置problem_id
     *
     * @param problemId problem_id
     */
    public void setProblemId(String problemId) {
        this.problemId = problemId;
    }

    /**
     * 获取language_id
     *
     * @return language_id - language_id
     */
    public Integer getLanguageId() {
        return languageId;
    }

    /**
     * 设置language_id
     *
     * @param languageId language_id
     */
    public void setLanguageId(Integer languageId) {
        this.languageId = languageId;
    }

    /**
     * 获取user_id
     *
     * @return user_id - user_id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置user_id
     *
     * @param userId user_id
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取submit_time
     *
     * @return submit_time - submit_time
     */
    public Date getSubmitDate() {
        return submitDate;
    }

    /**
     * 设置submit_time
     *
     * @param submitDate submit_time
     */
    public void setSubmitDate(Date submitDate) {
        this.submitDate = submitDate;
    }

    /**
     * 获取user_code
     *
     * @return user_code - user_code
     */
    public String getUserCode() {
        return userCode;
    }

    /**
     * 设置user_code
     *
     * @param userCode user_code
     */
    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
}