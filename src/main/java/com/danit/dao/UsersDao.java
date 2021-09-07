package com.danit.dao;


import com.danit.model.User;

import java.sql.*;

public class UsersDao {
    User currentUser;
    final private String USERNAME = "admin";
    final private String USERPASS = "admin123";
    final private String GET_USER_QUERY = "SELECT id, name, surname, email, photo FROM user_account WHERE email=? AND password=?";
    final private String GET_COOKIE_KEY_QUERY = "SELECT cookie_key FROM user_account WHERE email=? AND cookie_key = ?";
    final private String SET_COOKIE_KEY_QUERY = "UPDATE user_account SET cookie_key=? WHERE email=?";

    private Connection connection;
    public UsersDao() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://tinder.cbxr6gpev46o.us-east-2.rds.amazonaws.com:3306/tinder", USERNAME, USERPASS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isUserExist(String mail, String password) {
        boolean isUserExist = false;
        try {
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
                currentUser = new User(id, name, surname, email, photo);
                isUserExist = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isUserExist;
    }

    public boolean isValidCookieKey(String userEmail, String key) {
        boolean isValidCookieKey = false;
        try {
            PreparedStatement ps = connection.prepareStatement(GET_COOKIE_KEY_QUERY);
            ps.setString(1, userEmail);
            ps.setString(2, key);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                isValidCookieKey = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isValidCookieKey;
    }

    public void setUserCookieKey(String userEmail, String key) {
        try {
            PreparedStatement ps = connection.prepareStatement(SET_COOKIE_KEY_QUERY);
            ps.setString(1, key);
            ps.setString(2, userEmail);
            int result = ps.executeUpdate();
            if (result == 1) {
                System.out.println("successful cookie set");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public User getCurrentUser() {
        return currentUser;
    }
}
