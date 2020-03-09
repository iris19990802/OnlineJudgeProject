package com.iris.java.onlinejudge.judger.utils.Enums;

import java.util.EnumSet;
import java.util.HashMap;

public enum EventTag {

    SubmissionCreated(0, "SubmissionCreated"),

    CompileStart(1, "CompileStart"),
    //CompileFinished(2, "CompileFinished"),
    CompileSucceed(2, "CompileSucceed"),
    CompileFailed(3, "CompileFailed"),

    OneCaseFinished(4, "OneCaseFinished"),

    TaskFinished(5, "TaskFinished"),

    SystemError(6, "SystemError");

    public final Integer value;
    public final String eventName;

    EventTag(Integer value, String eventName){
        this.value = value;
        this.eventName = eventName;
    }

}
