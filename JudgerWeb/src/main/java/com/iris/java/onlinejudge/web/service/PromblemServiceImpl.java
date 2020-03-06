package com.iris.java.onlinejudge.web.service;

import com.iris.java.onlinejudge.web.mapper.normal.ProblemsMapper;
import com.iris.java.onlinejudge.web.pojo.db.Problems;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;


@Service
public class PromblemServiceImpl implements ProblemService{

    @Autowired
    ProblemsMapper problemsMapper;


    @Override
    public List<Problems> queryAllProblems() {

        // 查询所有题目（按 problemId 递增顺序）
        Example example = new Example(Problems.class);
        example.orderBy("problemId").asc();

        List<Problems> res = problemsMapper.selectByExample(example);

        return res;
    }

    @Override
    public Problems queryProblemById(String problemId) {

        Problems res = problemsMapper.selectByPrimaryKey(problemId);

        return res;
    }
}
