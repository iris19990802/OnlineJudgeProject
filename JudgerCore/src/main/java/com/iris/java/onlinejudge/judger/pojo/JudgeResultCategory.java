package com.iris.java.onlinejudge.judger.pojo;

import javax.persistence.*;

@Table(name = "judge_result_category")
public class JudgeResultCategory {
    /**
     * result_category_id
     */
    @Id
    @Column(name = "result_category_id")
    private Integer resultCategoryId;

    /**
     * simplified_name
     */
    @Column(name = "simplified_name")
    private String simplifiedName;

    /**
     * complete_name
     */
    @Column(name = "complete_name")
    private String completeName;

    /**
     * 获取result_category_id
     *
     * @return result_category_id - result_category_id
     */
    public Integer getResultCategoryId() {
        return resultCategoryId;
    }

    /**
     * 设置result_category_id
     *
     * @param resultCategoryId result_category_id
     */
    public void setResultCategoryId(Integer resultCategoryId) {
        this.resultCategoryId = resultCategoryId;
    }

    /**
     * 获取simplified_name
     *
     * @return simplified_name - simplified_name
     */
    public String getSimplifiedName() {
        return simplifiedName;
    }

    /**
     * 设置simplified_name
     *
     * @param simplifiedName simplified_name
     */
    public void setSimplifiedName(String simplifiedName) {
        this.simplifiedName = simplifiedName;
    }

    /**
     * 获取complete_name
     *
     * @return complete_name - complete_name
     */
    public String getCompleteName() {
        return completeName;
    }

    /**
     * 设置complete_name
     *
     * @param completeName complete_name
     */
    public void setCompleteName(String completeName) {
        this.completeName = completeName;
    }
}