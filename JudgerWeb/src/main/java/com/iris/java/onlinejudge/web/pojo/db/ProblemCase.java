package com.iris.java.onlinejudge.web.pojo.db;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "problem_case")
public class ProblemCase {
    /**
     * problem_id 所属题目id（外链接）
     */
    @Id
    @Column(name = "problem_id")
    private String problemId;

    /**
     * checkpoint_id 此题目的第几个测试点
     */
    @Id
    @Column(name = "checkpoint_id")
    private String checkpointId;

    /**
     * checkpoint_score 此测试点分值
     */
    @Column(name = "checkpoint_score")
    private Integer checkpointScore;

    /**
     * checkpoint_input
     */
    @Column(name = "checkpoint_input")
    private String checkpointInput;

    /**
     * checkpoint_output (可能是序列化过后的值）
     */
    @Column(name = "checkpoint_output")
    private String checkpointOutput;

    /**
     * 获取problem_id 所属题目id（外链接）
     *
     * @return problem_id - problem_id 所属题目id（外链接）
     */
    public String getProblemId() {
        return problemId;
    }

    /**
     * 设置problem_id 所属题目id（外链接）
     *
     * @param problemId problem_id 所属题目id（外链接）
     */
    public void setProblemId(String problemId) {
        this.problemId = problemId;
    }

    /**
     * 获取checkpoint_id 此题目的第几个测试点
     *
     * @return checkpoint_id - checkpoint_id 此题目的第几个测试点
     */
    public String getCheckpointId() {
        return checkpointId;
    }

    /**
     * 设置checkpoint_id 此题目的第几个测试点
     *
     * @param checkpointId checkpoint_id 此题目的第几个测试点
     */
    public void setCheckpointId(String checkpointId) {
        this.checkpointId = checkpointId;
    }

    /**
     * 获取checkpoint_score 此测试点分值
     *
     * @return checkpoint_score - checkpoint_score 此测试点分值
     */
    public Integer getCheckpointScore() {
        return checkpointScore;
    }

    /**
     * 设置checkpoint_score 此测试点分值
     *
     * @param checkpointScore checkpoint_score 此测试点分值
     */
    public void setCheckpointScore(Integer checkpointScore) {
        this.checkpointScore = checkpointScore;
    }

    /**
     * 获取checkpoint_input
     *
     * @return checkpoint_input - checkpoint_input
     */
    public String getCheckpointInput() {
        return checkpointInput;
    }

    /**
     * 设置checkpoint_input
     *
     * @param checkpointInput checkpoint_input
     */
    public void setCheckpointInput(String checkpointInput) {
        this.checkpointInput = checkpointInput;
    }

    /**
     * 获取checkpoint_output (可能是序列化过后的值）
     *
     * @return checkpoint_output - checkpoint_output (可能是序列化过后的值）
     */
    public String getCheckpointOutput() {
        return checkpointOutput;
    }

    /**
     * 设置checkpoint_output (可能是序列化过后的值）
     *
     * @param checkpointOutput checkpoint_output (可能是序列化过后的值）
     */
    public void setCheckpointOutput(String checkpointOutput) {
        this.checkpointOutput = checkpointOutput;
    }
}