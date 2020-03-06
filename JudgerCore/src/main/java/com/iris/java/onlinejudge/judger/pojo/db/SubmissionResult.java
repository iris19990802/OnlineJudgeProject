package com.iris.java.onlinejudge.judger.pojo.db;

import java.util.Date;
import javax.persistence.*;

@Table(name = "submission_result")
public class SubmissionResult {
    /**
     * submission_id 与submission_id一一对应
     */
    @Id
    @Column(name = "submission_id")
    private String submissionId;

    /**
     * submission_used_time 程序消耗时间
     */
    @Column(name = "result_used_time")
    private Long resultUsedTime;

    /**
     * submission_used_memory 程序消耗内存
     */
    @Column(name = "result_used_memory")
    private Long resultUsedMemory;

    /**
     * submission_category_id 运行结果（WA/AC/)
     */
    @Column(name = "result_category_id")
    private Integer resultStatus;

    /**
     * submission_score 最终分数
     */
    @Column(name = "result_score")
    private Integer resultScore;

    /**
     * excute_start_time 开始评测的时间
     */
    @Column(name = "excute_start_time")
    private Date excuteStartTime;

    public String getSubmissionId() {
        return submissionId;
    }

    public void setSubmissionId(String submissionId) {
        this.submissionId = submissionId;
    }

    /**
     * 获取submission_used_time 程序消耗时间
     *
     * @return result_used_time - submission_used_time 程序消耗时间
     */
    public Long getResultUsedTime() {
        return resultUsedTime;
    }

    /**
     * 设置submission_used_time 程序消耗时间
     *
     * @param resultUsedTime submission_used_time 程序消耗时间
     */
    public void setResultUsedTime(Long resultUsedTime) {
        this.resultUsedTime = resultUsedTime;
    }

    /**
     * 获取submission_used_memory 程序消耗内存
     *
     * @return result_used_memory - submission_used_memory 程序消耗内存
     */
    public Long getResultUsedMemory() {
        return resultUsedMemory;
    }

    /**
     * 设置submission_used_memory 程序消耗内存
     *
     * @param resultUsedMemory submission_used_memory 程序消耗内存
     */
    public void setResultUsedMemory(Long resultUsedMemory) {
        this.resultUsedMemory = resultUsedMemory;
    }

    /**
     * 获取submission_category_id 运行结果（WA/AC/)
     *
     * @return result_category_id - submission_category_id 运行结果（WA/AC/)
     */
    public Integer getResultStatus() {
        return resultStatus;
    }

    /**
     * 设置submission_category_id 运行结果（WA/AC/)
     *
     * @param resultStatus submission_category_id 运行结果（WA/AC/)
     */
    public void setResultStatus(Integer resultStatus) {
        this.resultStatus = resultStatus;
    }

    /**
     * 获取submission_score 最终分数
     *
     * @return result_score - submission_score 最终分数
     */
    public Integer getResultScore() {
        return resultScore;
    }

    /**
     * 设置submission_score 最终分数
     *
     * @param resultScore submission_score 最终分数
     */
    public void setResultScore(Integer resultScore) {
        this.resultScore = resultScore;
    }

    /**
     * 获取excute_start_time 开始评测的时间
     *
     * @return excute_start_time - excute_start_time 开始评测的时间
     */
    public Date getExcuteStartTime() {
        return excuteStartTime;
    }

    /**
     * 设置excute_start_time 开始评测的时间
     *
     * @param excuteStartTime excute_start_time 开始评测的时间
     */
    public void setExcuteStartTime(Date excuteStartTime) {
        this.excuteStartTime = excuteStartTime;
    }
}