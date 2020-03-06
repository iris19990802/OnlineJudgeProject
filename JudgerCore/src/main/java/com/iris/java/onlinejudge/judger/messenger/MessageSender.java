package com.iris.java.onlinejudge.judger.messenger;

import com.iris.java.onlinejudge.judger.pojo.message.HeartbeatNotifyMessage;
import com.iris.java.onlinejudge.judger.pojo.message.SubmissionNotifyMessage;

public interface MessageSender {

    public void judgeResultSender(SubmissionNotifyMessage message);

    public void heartbeatSender(HeartbeatNotifyMessage message);
}
