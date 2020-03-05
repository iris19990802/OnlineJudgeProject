package com.iris.java.onlinejudge.judger.utils.Enums;

public enum EventTag {

    SubmissionCreated(0, "SubmissionCreated"),
    CompileStart(1, "CompileStart"),
    CompileFinished(2, "CompileFinished"),
    OneCaseFinished(3, "OneCaseFinished"),
    TaskFinished(4, "TaskFinished");

    public final Integer value;
    public final String eventName;

    EventTag(Integer value, String eventName){
        this.value = value;
        this.eventName = eventName;
    }
}
