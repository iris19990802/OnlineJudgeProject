package com.iris.java.onlinejudge.judger.service;


import com.iris.java.onlinejudge.judger.pojo.db.Problems;

import java.util.List;


public interface ProblemService {

    List<Problems> queryAllProblems();

    Problems queryProblemById(String problemId);
}
