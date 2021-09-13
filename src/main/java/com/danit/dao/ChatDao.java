package com.danit.dao;

import com.danit.model.Message;
import com.danit.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChatDao {
    final private String USERNAME = "admin";
    final private String USERPASS = "admin123";
    final private String GET_CURRENT_CHAT_MESSAGES = "select * from message where conversation_id=?;";
    final private String SAVE_MESSAGE = "insert into tinder.message(user_account_id, conversation_id, text) values (?,?,?);";
    final private String CONNECTION_URL = "jdbc:mysql://tinder.cbxr6gpev46o.us-east-2.rds.amazonaws.com:3306/tinder";
    final private String GET_CHAT_INTERLOCUTORS = "select * from conversation where id=?;";
    final private String GET_CHAT_USERS = "SELECT * FROM user_account WHERE id=? or id=?;";

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
                Message message = new Message(chatId,text,senderId);
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<User> getChatInterlocutors(int chatId){
        List<User> chatInterlocutors = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(CONNECTION_URL, USERNAME, USERPASS)) {
            PreparedStatement statement = connection.prepareStatement(GET_CHAT_INTERLOCUTORS);
            statement.setInt(1, chatId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                int user1 = Integer.parseInt(resultSet.getString("user_account_id"));
                int user2 = Integer.parseInt(resultSet.getString("participant_id"));
                PreparedStatement statement1 = connection.prepareStatement(GET_CHAT_USERS);
                statement1.setInt(1,user1);
                statement1.setInt(2,user2);
                ResultSet resultSet1 = statement1.executeQuery();
                while (resultSet1.next()){

                    int id = Integer.parseInt(resultSet1.getString("id"));
                    String name = resultSet1.getString("name");
                    String surname = resultSet1.getString("surname");
                    String email = resultSet1.getString("email");
                    String photo = resultSet1.getString("photo");
                    String profession = resultSet1.getString("profession");
                    chatInterlocutors.add(new User(id, name, surname, email, photo, profession));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (ArrayList<User>) chatInterlocutors;
    }


}
