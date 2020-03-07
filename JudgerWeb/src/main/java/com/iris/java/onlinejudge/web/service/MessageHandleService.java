package com.iris.java.onlinejudge.web.service;

import com.iris.java.onlinejudge.web.pojo.message.SubmissionNotifyMessage;
import com.iris.java.onlinejudge.web.utils.Enums.EventTag;

public interface MessageHandleService { //(接口中不能定义私有方法)

    public void switchMessage(SubmissionNotifyMessage submissionNotifyMessage);

    void SubmissionCreated(SubmissionNotifyMessage submissionNotifyMessage);

    void CompileStart(SubmissionNotifyMessage submissionNotifyMessage);

    void CompileSucceed(SubmissionNotifyMessage submissionNotifyMessage);

    void CompileFailed(SubmissionNotifyMessage submissionNotifyMessage);

    void OneCaseFinished(SubmissionNotifyMessage submissionNotifyMessage);

    void TaskFinished(SubmissionNotifyMessage submissionNotifyMessage);

    void SystemError(SubmissionNotifyMessage submissionNotifyMessage);
}
