package com.danit.model;

public class Message {
    private int chatId;
    private String text;
    private int senderId;
    private Number timeAdded;

    public Message(int chatId, String text, int senderId) {
        this.chatId = chatId;
        this.text = text;
        this.senderId = senderId;
    }

    public int getChatId() {
        return chatId;
    }

    public void setChatId(int chatId) {
        this.chatId = chatId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    @Override
    public String toString() {
        return "Message{" +
                "chatId=" + chatId +
                ", text='" + text + '\'' +
                ", senderId=" + senderId +
                '}';
    }
}
