# **WebSocket部分代码**

## **根据SubmissionId订阅**
- 只能收到当前 submission 的结果
```java

@Component
// 订阅某submissionId
@ServerEndpoint(value="/ws/submission/querySubmitId/{submitId}")
public class SubmitIdSubscribeServer {

    // 存储在线Session（按submissionId，存储session）
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

        // 按submissionId，查询出Session，这些就是消息要发还的对象
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

```