package com.iris.java.onlinejudge.judger.service;

import com.iris.java.onlinejudge.judger.pojo.db.ProblemCase;

import java.util.List;

public interface ProblemCaseService {

    List<ProblemCase> queryCasesByProblemId(String problemId);
}
