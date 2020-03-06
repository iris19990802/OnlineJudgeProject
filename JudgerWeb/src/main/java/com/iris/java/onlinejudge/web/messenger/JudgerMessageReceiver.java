package com.iris.java.onlinejudge.web.messenger;

import com.iris.java.onlinejudge.web.pojo.message.SubmissionNotifyMessage;

public interface JudgerMessageReceiver {

    public void JudgeResultReceive(String submissionResultMessageJSON);

    public void HeartbeatReceive(String heartbeatJson);
}
