package com.iris.java.onlinejudge.web.pojo.message;

// 用于消息队列封装信息

import com.iris.java.onlinejudge.web.pojo.bean.ResultTask;
import com.iris.java.onlinejudge.web.utils.Enums.EventTag;
import com.iris.java.onlinejudge.web.utils.Enums.JudgeResultTag;

/**
 * 用途：
 *
 * 1、接收 Rabbitmq 发来的评测结果信息
 *
 * 2、把它传给用户端（非终态则缓存，终态则入库）
 *
 */
public class SubmissionNotifyMessage {

    // TODO : 是否需要定义错误时的返回方式？（this.error()）

    private String submissionId; //提交id

    private Integer status; //正在评测的submission的状态(Pending? AC/CE/WA...?) —————— 通过这个来判断：是否是终态

    private Integer eventId; // 测评事件类型（枚举类），标志"显示给用户"的信息

    private ResultTask data;// 具体数据（如果事件是"TaskFinished/oneCaseFinished",则解析数据,否则为null）


    public SubmissionNotifyMessage(Integer eventId, String submissionId, Integer status, ResultTask data) {
        this.status = status;
        this.submissionId = submissionId;
        this.eventId = eventId;
        this.data = data;
    }

    /**
     * 业务正常时返回的消息
     * (即使Case出了问题，也是属于正常业务范围内的)
     * @param data
     * @return
     */
    public static  SubmissionNotifyMessage normal(Integer eventId, String submissionId, Integer status, ResultTask data){
        return new SubmissionNotifyMessage(eventId,submissionId,status,data);
    }

    /**
     * 是否是结束消息
     * @return
     */
    public boolean isEndMessage() {
        if(status.equals(JudgeResultTag.PD.value)){
            return false;
        }
        else{
            return true;
        }

    }


    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public String getSubmissionId() {
        return submissionId;
    }

    public void setSubmissionId(String submissionId) {
        this.submissionId = submissionId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public ResultTask getData() {
        return data;
    }

    public void setData(ResultTask data) {
        this.data = data;
    }

}
