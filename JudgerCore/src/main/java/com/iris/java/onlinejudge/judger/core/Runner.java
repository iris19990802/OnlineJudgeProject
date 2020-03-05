package com.iris.java.onlinejudge.judger.core;

import com.iris.java.onlinejudge.judger.pojo.bean.TaskCase;
import org.springframework.beans.factory.annotation.Value;

public class Runner {


    //public void getRunningResultOneCase(TaskCase taskCase);


    @Value("${juegecode.timeLimit}")
    Integer JUDGE_TIME_LIMIT;

    @Value("${judgecode.memoryLimit}")
    Integer JUDGE_MEMORY_LIMIT;

}
