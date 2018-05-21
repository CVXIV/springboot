package com.example.springboot.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;

import java.util.concurrent.ConcurrentHashMap;
@Component
public class WebSocketPush implements WebSocketHandler {
    private static final Logger logger= LoggerFactory.getLogger(WebSocketPush.class);
    private static final ConcurrentHashMap<Integer,WebSocketSession> users=new ConcurrentHashMap<>();
    private static final String index="user";
    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {
        Integer userId= getUserId(webSocketSession);
        if(userId!=null){
            users.put(userId,webSocketSession);
            logger.info("建立webSocket连接");
            webSocketSession.sendMessage(new TextMessage("成功建立连接"));
        }else{
            if(webSocketSession.isOpen())
                webSocketSession.close();
        }
    }

    @Override
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) {

    }

    @Override
    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable){
        users.remove(getUserId(webSocketSession));
        logger.info("连接出现错误:" + throwable.toString());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) {
        users.remove(getUserId(webSocketSession));
        logger.info("断开webSocket连接");
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    private Integer getUserId(WebSocketSession webSocketSession){
        return (Integer)webSocketSession.getAttributes().get(index);
    }

    public void sendMessageToAll(String message) {
        synchronized (users){
            for(WebSocketSession session:users.values()){
                sendMessage(message,session);
            }
        }
    }

    public void sendMessageToOne(String message,int user){
        sendMessage(message,users.get(user));
    }

    public void sendErrorMessageToOne(String message,int user){
        sendMessage(message,users.get(user));
        try {
            users.get(user).close();
        } catch (Exception e) {
            logger.info("session已经关闭");
        }
    }

    private void sendMessage(String message,WebSocketSession session){
        try{
            if(session!=null){
                session.sendMessage(new TextMessage(message));
            }
        }catch (Exception e){
            logger.info("session异常");
        }
    }
}
