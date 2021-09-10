package com.danit.controllers;

import com.danit.model.MessageDecoder;
import com.danit.model.MessageEncoder;
import com.danit.model.Message;

import static java.lang.String.format;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;


@ServerEndpoint(value = "/chatSocket/{chatId}", encoders = MessageEncoder.class, decoders = MessageDecoder.class)
public class WebSocketChat {

    static Set<Session> peers = Collections.synchronizedSet(new HashSet<Session>());

    @OnOpen
    public void onOpen(@PathParam("chatId") int chatID, Session session) {
        System.out.println(format("%s joined the chat room.", session.getId()));
        session.getUserProperties().put("chatId", chatID);
        System.out.println(session);
        peers.add(session);
    }

    @OnMessage
    public void onMessage(Message message, Session session) throws IOException, EncodeException {
        System.out.println("text = " + message.getText());
        System.out.println("sender id = " + message.getSenderId());
        System.out.println("chat id = " + session.getUserProperties().get("chatId"));

        for (Session peer : peers) {
            if (session.getUserProperties().get("chatId") == peer.getUserProperties().get("chatId")) {
                if (session.getId() != peer.getId()) {
                    peer.getBasicRemote().sendObject(message);
                }
            }
        }
    }

    @OnClose
    public void onClose(Session session) throws IOException, EncodeException {
        System.out.println(format("%s left the chat room.", session.getId()));
        peers.remove(session);
    }

}