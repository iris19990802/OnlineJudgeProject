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


    /**
     * java 枚举 反向查找
     *
     *（通过type，得到枚举对象本身）
     */
    private static final HashMap<Integer,EventTag> mp = new HashMap<>();

    static {
        for(EventTag e : EnumSet.allOf(EventTag.class)){
            mp.put(e.value, e);
        }
    }

    public static EventTag getIndex(Integer id){
        return mp.get(id);
    }

}
