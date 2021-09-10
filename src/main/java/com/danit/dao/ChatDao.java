package com.danit.dao;

import com.danit.model.Message;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChatDao {
    final private String USERNAME = "admin";
    final private String USERPASS = "admin123";
    final private String GET_CURRENT_CHAT_MESSAGES = "select * from message where conversation_id=?;";
    final private String SAVE_MESSAGE = "insert into tinder.message(user_account_id, conversation_id, text, time_added) values (?,?,?,?);";
    final private String CONNECTION_URL = "jdbc:mysql://tinder.cbxr6gpev46o.us-east-2.rds.amazonaws.com:3306/tinder";


    public List<Message> getCurrentChatMessages(int currentChatId){
        List<Message> chatMessages = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(CONNECTION_URL, USERNAME, USERPASS)) {
            PreparedStatement statement = connection.prepareStatement(GET_CURRENT_CHAT_MESSAGES);
            statement.setInt(1, currentChatId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                int chatId = Integer.parseInt(resultSet.getString("conversation_id"));
                String text = resultSet.getString("text");
                int senderId = Integer.parseInt(resultSet.getString("user_account_id"));
                Number timeAdded = Integer.parseInt(resultSet.getString("time_added"));
                Message message = new Message(chatId,text,senderId,timeAdded);
                chatMessages.add(message);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return chatMessages;
    }

    public void saveMessage(Message message){
        List<Message> chatMessages = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(CONNECTION_URL, USERNAME, USERPASS)) {
            PreparedStatement statement = connection.prepareStatement(SAVE_MESSAGE);
            statement.setInt(1, message.getSenderId());
            statement.setInt(2, message.getChatId());
            statement.setString(3, message.getText());
            statement.setTimestamp(4, new Timestamp((Long) message.getTimeAdded()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
