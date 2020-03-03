package com.iris.java.onlinejudge.judger.pojo.vo;

import java.util.Date;
import java.util.List;

public class JudgeResultVO {

    private String resultId;

    private Integer resultUsedTime;

    private Integer resultUsedMemory;

    private Integer resultCategoryId;

    private Integer resultScore;

    private Date excuteStartTime;

    private List<ResultCaseVO> cases;
}
