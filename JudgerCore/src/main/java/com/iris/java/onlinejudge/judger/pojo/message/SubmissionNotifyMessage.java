package com.iris.java.onlinejudge.judger.pojo.message;

// 用于消息队列封装信息

public class SubmissionNotifyMessage<T> {

    // TODO : 是否需要定义错误时的返回方式？（this.error()）

    private Integer eventId; // 测评事件类型（枚举类）

    private String submissionId; //提交id

    private Integer status; //正在评测的submission的状态(Pending? AC/CE/WA...?)

    private T data;// 具体数据（泛型）


    public SubmissionNotifyMessage(Integer eventId, String submissionId, Integer status, T data) {
        this.status = status;
        this.submissionId = submissionId;
        this.eventId = eventId;
        this.data = data;
    }

    /**
     * 业务正常时返回的消息
     * (即使Case出了问题，也是属于正常业务范围内的)
     * @param data
     * @param <T>
     * @return
     */
    public static <T> SubmissionNotifyMessage<T> normal(Integer eventId, String submissionId, Integer status, T data){
        return new SubmissionNotifyMessage<T>(eventId,submissionId,status,data);
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
