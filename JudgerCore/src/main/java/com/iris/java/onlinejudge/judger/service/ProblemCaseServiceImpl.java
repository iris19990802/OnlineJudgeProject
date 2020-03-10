package com.iris.java.onlinejudge.judger.service;

import com.iris.java.onlinejudge.judger.mapper.normal.ProblemCaseMapper;
import com.iris.java.onlinejudge.judger.pojo.db.ProblemCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class ProblemCaseServiceImpl implements ProblemCaseService{

    @Autowired
    ProblemCaseMapper problemCaseMapper;

    @Override
    public List<ProblemCase> queryCasesByProblemId(String problemId) {

        Example example = new Example(ProblemCase.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("problemId",problemId);
        List<ProblemCase> problemCases = problemCaseMapper.selectByExample(example);
        
        return problemCases;
    }
}
