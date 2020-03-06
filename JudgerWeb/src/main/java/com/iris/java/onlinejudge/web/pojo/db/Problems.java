package com.iris.java.onlinejudge.web.pojo.db;

import javax.persistence.Column;
import javax.persistence.Id;

public class Problems {
    /**
     * problem_id 题目id
     */
    @Id
    @Column(name = "problem_id")
    private Long problemId;

    /**
     * problem_name 题目名称
     */
    @Column(name = "problem_name")
    private String problemName;

    /**
     * problem_time_limit 时间限制
     */
    @Column(name = "problem_time_limit")
    private Integer problemTimeLimit;

    /**
     * problem_memory_limit 内存限制
     */
    @Column(name = "problem_memory_limit")
    private Integer problemMemoryLimit;

    /**
     * problem_description
     */
    @Column(name = "problem_description")
    private String problemDescription;


    @Column(name = "problem_input_description")
    private String problemInputDescription;

    @Column(name = "problem_output_description")
    private String problemOutputDescription;

    @Column(name = "problem_sample_input")
    private String problemSampleInput;

    @Column(name = "problem_sample_output")
    private String problemSampleOutput;

    @Column(name = "problem_hint")
    private String problemHint;

    public String getProblemInputDescription() {
        return problemInputDescription;
    }

    public void setProblemInputDescription(String problemInputDescription) {
        this.problemInputDescription = problemInputDescription;
    }

    public String getProblemOutputDescription() {
        return problemOutputDescription;
    }

    public void setProblemOutputDescription(String problemOutputDescription) {
        this.problemOutputDescription = problemOutputDescription;
    }

    public String getProblemSampleInput() {
        return problemSampleInput;
    }

    public void setProblemSampleInput(String problemSampleInput) {
        this.problemSampleInput = problemSampleInput;
    }

    public String getProblemSampleOutput() {
        return problemSampleOutput;
    }

    public void setProblemSampleOutput(String problemSampleOutput) {
        this.problemSampleOutput = problemSampleOutput;
    }

    public String getProblemHint() {
        return problemHint;
    }

    public void setProblemHint(String problemHint) {
        this.problemHint = problemHint;
    }


    /**
     * 获取problem_id 题目id
     *
     * @return problem_id - problem_id 题目id
     */
    public Long getProblemId() {
        return problemId;
    }

    /**
     * 设置problem_id 题目id
     *
     * @param problemId problem_id 题目id
     */
    public void setProblemId(Long problemId) {
        this.problemId = problemId;
    }

    /**
     * 获取problem_name 题目名称
     *
     * @return problem_name - problem_name 题目名称
     */
    public String getProblemName() {
        return problemName;
    }

    /**
     * 设置problem_name 题目名称
     *
     * @param problemName problem_name 题目名称
     */
    public void setProblemName(String problemName) {
        this.problemName = problemName;
    }

    /**
     * 获取problem_time_limit 时间限制
     *
     * @return problem_time_limit - problem_time_limit 时间限制
     */
    public Integer getProblemTimeLimit() {
        return problemTimeLimit;
    }

    /**
     * 设置problem_time_limit 时间限制
     *
     * @param problemTimeLimit problem_time_limit 时间限制
     */
    public void setProblemTimeLimit(Integer problemTimeLimit) {
        this.problemTimeLimit = problemTimeLimit;
    }

    /**
     * 获取problem_memory_limit 内存限制
     *
     * @return problem_memory_limit - problem_memory_limit 内存限制
     */
    public Integer getProblemMemoryLimit() {
        return problemMemoryLimit;
    }

    /**
     * 设置problem_memory_limit 内存限制
     *
     * @param problemMemoryLimit problem_memory_limit 内存限制
     */
    public void setProblemMemoryLimit(Integer problemMemoryLimit) {
        this.problemMemoryLimit = problemMemoryLimit;
    }

    /**
     * 获取problem_description
     *
     * @return problem_description - problem_description
     */
    public String getProblemDescription() {
        return problemDescription;
    }

    /**
     * 设置problem_description
     *
     * @param problemDescription problem_description
     */
    public void setProblemDescription(String problemDescription) {
        this.problemDescription = problemDescription;
    }
}