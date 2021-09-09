package com.danit.dao;

import com.danit.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsersDao {
    User currentUser;
    List users = new ArrayList<User>();
    final private String USERNAME = "admin";
    final private String USERPASS = "admin123";
    final private String QUERY_GET_USERS = "SELECT * FROM user_account WHERE id != ?";
    final private String GET_USER_QUERY = "SELECT id, name, surname, email, photo, profession FROM user_account WHERE email=? AND password=?";
    final private String GET_COOKIE_KEY_QUERY = "SELECT * FROM user_account WHERE email=? AND cookie_key = ?";
    final private String SET_COOKIE_KEY_QUERY = "UPDATE user_account SET cookie_key=? WHERE email=?";
    final private String CONNECTION_URL = "jdbc:mysql://tinder.cbxr6gpev46o.us-east-2.rds.amazonaws.com:3306/tinder";

    public boolean isUserExist(String mail, String password) {
        boolean isUserExist = false;
        try (Connection connection = DriverManager.getConnection(CONNECTION_URL, USERNAME, USERPASS)) {
            PreparedStatement ps = connection.prepareStatement(GET_USER_QUERY);
            ps.setString(1, mail);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String email = rs.getString("email");
                String photo = rs.getString("photo");
                String profession = rs.getString("profession");
                currentUser = new User(id, name, surname, email, photo, profession);
                isUserExist = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isUserExist;
    }

    public boolean isValidCookieKey(String userEmail, String key) {
        boolean isValidCookieKey = false;
        try (Connection connection = DriverManager.getConnection(CONNECTION_URL, USERNAME, USERPASS)) {
            PreparedStatement ps = connection.prepareStatement(GET_COOKIE_KEY_QUERY);
            ps.setString(1, userEmail);
            ps.setString(2, key);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                isValidCookieKey = true;
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String email = rs.getString("email");
                String photo = rs.getString("photo");
                String profession = rs.getString("profession");
                currentUser = new User(id, name, surname, email, photo, profession);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isValidCookieKey;
    }

    public void setUserCookieKey(String userEmail, String key) {
        try (Connection connection = DriverManager.getConnection(CONNECTION_URL, USERNAME, USERPASS)) {
            PreparedStatement ps = connection.prepareStatement(SET_COOKIE_KEY_QUERY);
            ps.setString(1, key);
            ps.setString(2, userEmail);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public List<User> getUsers(User currentUser){
        try (Connection connection = DriverManager.getConnection(CONNECTION_URL, USERNAME, USERPASS)) {
            PreparedStatement statement = connection.prepareStatement(QUERY_GET_USERS);
            statement.setInt(1, currentUser.getId());
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                int id = Integer.parseInt(resultSet.getString("id"));
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String email = resultSet.getString("email");
                String photo = resultSet.getString("photo");
                String profession = resultSet.getString("profession");
                users.add(new User(id, name, surname, email, photo, profession));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
}
