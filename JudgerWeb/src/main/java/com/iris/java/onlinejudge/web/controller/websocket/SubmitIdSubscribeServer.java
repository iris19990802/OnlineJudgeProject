package com.iris.java.onlinejudge.web.controller.websocket;


import com.alibaba.fastjson.JSON;
import com.iris.java.onlinejudge.web.pojo.message.SubmissionNotifyMessage;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;


@Component

// 订阅某submissionId
@ServerEndpoint(value="/submission/querySubmitId/{submitId}")
public class SubmitIdSubscribeServer {

    // 存储在线Session
    private static ConcurrentHashMap<String,SubmitIdSubscribeServer> webSocketSet = new ConcurrentHashMap<>();

    private String submitId;
    private Session session;

    @OnOpen
    public void onOpen(@PathParam("submitId")String submitId,Session session){
        this.session = session;
        this.submitId = submitId;

        webSocketSet.put(submitId,this);
    }

    @OnClose
    public void onClose(){

        webSocketSet.remove(this.submitId);

    }

    // 把数据转成JSON发出去
    private void send(Object message) throws Exception{

        String jsonMessage = JSON.toJSONString(message);

        this.session.getBasicRemote().sendText(jsonMessage);
    }

    /**
     * 把评测结果发给相应 submitId 的订阅者
     * @param judgeResult
     * @throws Exception
     */
    public boolean sendJudgeResultBySubmissionId(SubmissionNotifyMessage judgeResult){

        String resultSubmitId = judgeResult.getSubmissionId();
        SubmitIdSubscribeServer thisServer = webSocketSet.get(resultSubmitId);

        // 如果用户在线，则发送消息,并返回true；
        // 用户不在线，则自动忽略，并返回false
        if(thisServer != null){
            try{
                thisServer.send(judgeResult);
            }catch(Exception e){
                e.printStackTrace();
            }
            return true;
        }
        return false;
    }

}
