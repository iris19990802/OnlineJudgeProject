package com.iris.java.onlinejudge.judger.utils.Enums;

public enum JudgeResultTag {

    AC(0, "AC", "Accepted"),
    PE(1, "PE", "Presentation Error"),
    TLE(2 , "TLE", "Time Limit Exceed"),
    MLE(3 , "MLE", "Memory Limit Exceed"),
    WA(4 , "WA", "Wrong Answer"),
    RE(5 , "RE", "Runtime Error"),
    OLE(6 , "OLE", "Output Limit Exceed"),
    CE(7 , "CE", "Compile Error"),
    SE(8 , "SE", "System Error");

    public final Integer type;
    public final String tag;
    public final String completeName;

    JudgeResultTag(Integer type, String tag,String completeName){
        this.type = type;
        this.tag = tag;
        this.completeName = completeName;
    }
}
