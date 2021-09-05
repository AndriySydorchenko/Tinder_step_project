package com.danit.dao;


import com.danit.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    public List<User> getUsers() throws SQLException {
        List users = new ArrayList<User>();
        Connection connection = DriverManager.getConnection("jdbc:mysql://tinder.cbxr6gpev46o.us-east-2.rds.amazonaws.com:3306/tinder?user=admin&password=admin123");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from user_account;");
        while (resultSet.next()){
            int id = Integer.parseInt(resultSet.getString("id"));
            String name = resultSet.getString("name");
            String surname = resultSet.getString("surname");
            String email = resultSet.getString("email");
            String password = resultSet.getString("password");
            String photo = resultSet.getString("photo");
            users.add(new User(id,name, surname,email,password, photo));
        }
        connection.close();
        return users;
    }
}
