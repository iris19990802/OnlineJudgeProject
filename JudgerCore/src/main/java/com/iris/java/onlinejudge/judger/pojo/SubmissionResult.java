package com.iris.java.onlinejudge.judger.pojo;

import java.util.Date;
import javax.persistence.*;

@Table(name = "submission_result")
public class SubmissionResult {
    /**
     * submission_id 与submission_id一一对应
     */
    @Id
    @Column(name = "result_id")
    private String resultId;

    /**
     * submission_used_time 程序消耗时间
     */
    @Column(name = "result_used_time")
    private Integer resultUsedTime;

    /**
     * submission_used_memory 程序消耗内存
     */
    @Column(name = "result_used_memory")
    private Integer resultUsedMemory;

    /**
     * submission_category_id 运行结果（WA/AC/)
     */
    @Column(name = "result_category_id")
    private Integer resultCategoryId;

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

    /**
     * 获取submission_id 与submission_id一一对应
     *
     * @return result_id - submission_id 与submission_id一一对应
     */
    public String getResultId() {
        return resultId;
    }

    /**
     * 设置submission_id 与submission_id一一对应
     *
     * @param resultId submission_id 与submission_id一一对应
     */
    public void setResultId(String resultId) {
        this.resultId = resultId;
    }

    /**
     * 获取submission_used_time 程序消耗时间
     *
     * @return result_used_time - submission_used_time 程序消耗时间
     */
    public Integer getResultUsedTime() {
        return resultUsedTime;
    }

    /**
     * 设置submission_used_time 程序消耗时间
     *
     * @param resultUsedTime submission_used_time 程序消耗时间
     */
    public void setResultUsedTime(Integer resultUsedTime) {
        this.resultUsedTime = resultUsedTime;
    }

    /**
     * 获取submission_used_memory 程序消耗内存
     *
     * @return result_used_memory - submission_used_memory 程序消耗内存
     */
    public Integer getResultUsedMemory() {
        return resultUsedMemory;
    }

    /**
     * 设置submission_used_memory 程序消耗内存
     *
     * @param resultUsedMemory submission_used_memory 程序消耗内存
     */
    public void setResultUsedMemory(Integer resultUsedMemory) {
        this.resultUsedMemory = resultUsedMemory;
    }

    /**
     * 获取submission_category_id 运行结果（WA/AC/)
     *
     * @return result_category_id - submission_category_id 运行结果（WA/AC/)
     */
    public Integer getResultCategoryId() {
        return resultCategoryId;
    }

    /**
     * 设置submission_category_id 运行结果（WA/AC/)
     *
     * @param resultCategoryId submission_category_id 运行结果（WA/AC/)
     */
    public void setResultCategoryId(Integer resultCategoryId) {
        this.resultCategoryId = resultCategoryId;
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