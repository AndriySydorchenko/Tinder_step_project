package com.danit.model;

public class Message {
    private int chatId;
    private String text;
    private int senderId;
    private Number timeAdded;

    public Message(int chatId, String text, int senderId, Number timeAdded) {
        this.chatId = chatId;
        this.text = text;
        this.senderId = senderId;
        this.timeAdded = timeAdded;
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

    public Number getTimeAdded() {
        return timeAdded;
    }

    public void setTimeAdded(Number timeAdded) {
        this.timeAdded = timeAdded;
    }

    @Override
    public String toString() {
        return "Message{" +
                "chatId=" + chatId +
                ", text='" + text + '\'' +
                ", senderId=" + senderId +
                ", timeAdded=" + timeAdded +
                '}';
    }
}
