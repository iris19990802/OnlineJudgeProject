package com.iris.java.onlinejudge.web.redis.PrefixKeys;


public class UserKey extends BasePrefix {

    public static final int TOKEN_EXPIRE = 3600*24;
    public UserKey(int expireSeconds,String prefix) {
        super(expireSeconds,prefix);
    }

    public static UserKey token = new UserKey(TOKEN_EXPIRE,"token");


    // 对象缓存，是希望它永久有效的（只要没发生变化）
    public static UserKey getBySubmissionId = new UserKey(0,"id");
}
