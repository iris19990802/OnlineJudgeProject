package com.iris.java.onlinejudge.web.redis.PrefixKeys;

public interface KeyPrefix {


    public int expireSeconds();

    public String getPrefix();

}
