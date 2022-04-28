package icu.websocket.service;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Component;

import cn.hutool.json.JSONUtil;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import icu.websocket.domain.SocketMessage;

@ServerEndpoint("/web/socket/{sid}")
@Component
public class WebSocketService {

    static Log log = LogFactory.get(WebSocketService.class);

    /**
     * record online user
     */
    private static int onlineCount = 0;

    /**
     * online user websocket
     */
    private static CopyOnWriteArraySet<WebSocketService> webSocketServices = new CopyOnWriteArraySet<WebSocketService>();

    private Session session;

    private String sid = "";

    @OnOpen
    public void onOpen(Session session, @PathParam("sid") String sid) {
        this.session = session;

        // 加入 set
        webSocketServices.add(this);

        // 在线人数 +1
        addOnlineCount();

        log.info(String.format("有新窗口开始监听: %s, 当前在线人数为 %d", sid, getOnlineCount()));

        this.sid = sid;

        try {
            sendMessage("连接成功");
        } catch (Exception e) {
            log.error("websocket IO异常");
        }

    }

    @OnClose
    public void onClose() {
        webSocketServices.remove(this);
        subOnlineCount();
        log.info(String.format("有一连接关闭! 当前在线人数为 %d", getOnlineCount()));
    }

    @OnMessage
    public void OnMessage(String message, Session session) {
        log.info(String.format("收到来自窗口 %s 的消息: %s", sid, message));

        for (WebSocketService item : webSocketServices) {
            try {
                item.sendMessage(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误");
        error.printStackTrace();
    }

    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    public void sendMessage(SocketMessage message) throws IOException {
        this.session.getBasicRemote().sendText(JSONUtil.toJsonStr(message));
    }

    public static void sendInfo(SocketMessage message, @PathParam("sid") String sid) throws IOException {
        log.info(String.format("推送消息到窗口: %s, 推送内容: %s", sid, message.toString()));

        for (WebSocketService item : webSocketServices) {
            try {
                if (sid == null) {
                    item.sendMessage(message);
                } else if (item.sid.equals(sid)) {
                    item.sendMessage(message);
                }
            } catch (IOException e) {
                continue;
            }
        }
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketService.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketService.onlineCount--;
    }

}
