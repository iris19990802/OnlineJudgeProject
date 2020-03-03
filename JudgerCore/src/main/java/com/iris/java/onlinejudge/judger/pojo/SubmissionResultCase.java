package com.iris.java.onlinejudge.judger.pojo;

import javax.persistence.*;

@Table(name = "submission_result_case")
public class SubmissionResultCase {
    /**
     * submission_result_id 与judge_result外链接
     */
    @Id
    @Column(name = "result_id")
    private String resultId;

    /**
     * case_id
     */
    @Id
    @Column(name = "case_id")
    private Integer caseId;

    /**
     * case_used_time
     */
    @Column(name = "case_used_time")
    private Integer caseUsedTime;

    /**
     * case_used_memory
     */
    @Column(name = "case_used_memory")
    private Integer caseUsedMemory;

    /**
     * 获取submission_result_id 与judge_result外链接
     *
     * @return result_id - submission_result_id 与judge_result外链接
     */
    public String getResultId() {
        return resultId;
    }

    /**
     * 设置submission_result_id 与judge_result外链接
     *
     * @param resultId submission_result_id 与judge_result外链接
     */
    public void setResultId(String resultId) {
        this.resultId = resultId;
    }

    /**
     * 获取case_id
     *
     * @return case_id - case_id
     */
    public Integer getCaseId() {
        return caseId;
    }

    /**
     * 设置case_id
     *
     * @param caseId case_id
     */
    public void setCaseId(Integer caseId) {
        this.caseId = caseId;
    }

    /**
     * 获取case_used_time
     *
     * @return case_used_time - case_used_time
     */
    public Integer getCaseUsedTime() {
        return caseUsedTime;
    }

    /**
     * 设置case_used_time
     *
     * @param caseUsedTime case_used_time
     */
    public void setCaseUsedTime(Integer caseUsedTime) {
        this.caseUsedTime = caseUsedTime;
    }

    /**
     * 获取case_used_memory
     *
     * @return case_used_memory - case_used_memory
     */
    public Integer getCaseUsedMemory() {
        return caseUsedMemory;
    }

    /**
     * 设置case_used_memory
     *
     * @param caseUsedMemory case_used_memory
     */
    public void setCaseUsedMemory(Integer caseUsedMemory) {
        this.caseUsedMemory = caseUsedMemory;
    }
}