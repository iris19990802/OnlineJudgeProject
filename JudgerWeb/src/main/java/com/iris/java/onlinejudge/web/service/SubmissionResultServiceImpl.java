package com.iris.java.onlinejudge.web.service;

import com.iris.java.onlinejudge.web.mapper.normal.SubmissionResultMapper;
import com.iris.java.onlinejudge.web.pojo.db.SubmissionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubmissionResultServiceImpl implements SubmissionResultService{

    @Autowired
    SubmissionResultMapper submissionResultMapper;

    @Override
    public void insertOneResult(SubmissionResult submissionResult) {
        submissionResultMapper.insert(submissionResult);
    }

}
