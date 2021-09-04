package com.danit.dao;


import com.danit.model.User;

import java.sql.*;

public class UsersDao {
    User currentUser;
    final private String USERNAME = "admin";
    final private String USERPASS = "admin123";
    final private String QUERY = "SELECT id, name, surname, email, photo FROM user_account WHERE email = ? AND password = ?";

    Connection connection;
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
            PreparedStatement ps = connection.prepareStatement(QUERY);
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

    public User getCurrentUser() {
        return currentUser;
    }
}
