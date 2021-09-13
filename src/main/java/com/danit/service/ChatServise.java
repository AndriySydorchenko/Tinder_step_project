package com.danit.service;

import com.danit.dao.ChatDao;
import com.danit.model.Message;
import com.danit.model.User;

import java.util.ArrayList;
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
    public ArrayList<User> getChatInterlocutors(int chatId){ return chatDao.getChatInterlocutors(chatId);}
}
