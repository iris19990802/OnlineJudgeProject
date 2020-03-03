package com.iris.java.onlinejudge.judger.messenger;

public interface MessageReceiver {

    // 被实现类里的Listener调用
    public void newSubmissionHandler();
}
