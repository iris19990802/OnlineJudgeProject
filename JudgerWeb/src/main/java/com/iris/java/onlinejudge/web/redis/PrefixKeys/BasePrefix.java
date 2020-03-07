package com.iris.java.onlinejudge.web.redis.PrefixKeys;

public abstract class BasePrefix implements KeyPrefix {

    private int expireSecond;

    private String prefix;

    public BasePrefix(String prefix){
        this(0,prefix);// 写死expireSecond为0，代表其永不过期
    }

    public BasePrefix(int expireSecond,String prefix){
        this.expireSecond = expireSecond;
        this.prefix = prefix;
    }

    @Override
    public int expireSeconds() { // 默认为0，代表永不过期
        return expireSecond;
    }


    // 要保证每一个模块，prefix都不一样。
    // 真正的prefix是：模块名（类名） + 这个对象都私有prefix（prefix）
    @Override
    public String getPrefix() {
        String className = getClass().getSimpleName();
        return className + ":" + prefix;
    }
}
