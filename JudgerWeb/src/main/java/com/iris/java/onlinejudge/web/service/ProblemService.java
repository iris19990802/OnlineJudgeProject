package com.iris.java.onlinejudge.web.service;


import com.iris.java.onlinejudge.web.mapper.normal.ProblemsMapper;
import com.iris.java.onlinejudge.web.pojo.db.Problems;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ProblemService {

    List<Problems> queryAllProblems();

    Problems queryProblemById(String problemId);
}
