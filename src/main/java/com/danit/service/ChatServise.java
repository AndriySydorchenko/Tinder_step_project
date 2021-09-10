package com.danit.service;

import com.danit.dao.ChatDao;
import com.danit.model.Message;

import java.util.List;

public class ChatServise {
    private ChatDao chatDao;

    public ChatServise() {
        this.chatDao = new ChatDao();
    }

    public List<Message> getCurrentChatMessages(int currentChatId){
        return chatDao.getCurrentChatMessages(currentChatId);
    }
    public void saveMessage(Message message){chatDao.saveMessage(message);}

}
