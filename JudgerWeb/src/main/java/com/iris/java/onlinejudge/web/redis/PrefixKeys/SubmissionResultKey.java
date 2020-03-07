package com.iris.java.onlinejudge.web.redis.PrefixKeys;

public class SubmissionResultKey extends BasePrefix{

    public static final int SUBMISSION_EXPIRE = 60; // 一分钟内有效

    public SubmissionResultKey(int expireSecond, String prefix) {
        super(expireSecond, prefix);
    }


    public static UserKey getById = new UserKey(SUBMISSION_EXPIRE,"submissionId");

}
