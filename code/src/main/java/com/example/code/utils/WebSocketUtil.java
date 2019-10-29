package com.example.code.utils;


import javax.websocket.RemoteEndpoint;
import javax.websocket.Session;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description: 记录在线用户和实现服务端向客户端发送消息
 * @create: 2019-10-29 14:06
 **/
public class WebSocketUtil {
    /**
     * 模拟存储   在线用户
     */
    public static final Map<String, Session> USERS_ONLINE = new ConcurrentHashMap<>();

    /**
     * 向所有在线用户发送消息(遍历 向每一个用户发送)
     * @param message
     */
    public static void sendMessageToAllOnlineUser(String message){
        USERS_ONLINE.forEach((username, Session) -> sendMessage(Session, message));
    }

    /**
     * 向指定用户发送消息
     * @param session 用户session
     * @param message 发送消息内容
     */
    private static void sendMessage(Session session, String message) {
        if (session == null) {
            return;
        }

        final RemoteEndpoint.Basic basic = session.getBasicRemote();
        if (basic == null) {
            return;
        }

        try {
            basic.sendText(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
