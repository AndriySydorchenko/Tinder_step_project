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
    final private String QUERY = "SELECT id, name, surname, email, photo, profession FROM user_account WHERE email = ? AND password = ?";
    final private String QUERY_GET_USERS = "SELECT * FROM user_account WHERE id != ?";

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
                String profession = rs.getString("profession");
                currentUser = new User(id, name, surname, email, photo, profession);
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

    public List<User> getUsers(User currentUser){
        try {
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
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }


}
