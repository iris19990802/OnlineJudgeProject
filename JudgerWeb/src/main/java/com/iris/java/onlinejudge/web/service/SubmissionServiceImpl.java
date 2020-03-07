package com.iris.java.onlinejudge.web.service;

import com.iris.java.onlinejudge.web.mapper.normal.SubmissionMapper;
import com.iris.java.onlinejudge.web.pojo.db.Submission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubmissionServiceImpl implements SubmissionService{

    @Autowired
    SubmissionMapper submissionMapper;

    @Override
    public void insertOneSubmission(Submission submission) {
        submissionMapper.insert(submission);
    }
}
