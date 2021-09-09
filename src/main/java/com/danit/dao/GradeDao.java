package com.danit.dao;

import com.danit.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GradeDao {
    List likedUsers = new ArrayList<User>();
    final private String USERNAME = "admin";
    final private String USERPASS = "admin123";
    final private String QUERY_INSERT = "REPLACE  INTO grade (idgrade, user_account_id, graded_user_account_id, grade)\n" +
            "  VALUES (?, ?, ?, ?);";
    final private String QUERY_GET_GRADE = "select * from grade\n" +
            "LEFT JOIN user_account as ua on grade.graded_user_account_id = ua.id " +
            "where grade.grade = 'Like' AND grade.user_account_id = ?;";

    Connection connection;
    public GradeDao() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://tinder.cbxr6gpev46o.us-east-2.rds.amazonaws.com:3306/tinder", USERNAME, USERPASS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void insertGrade(User currentUser, User gradeUser, String grade ) {

        try {
            PreparedStatement ps = connection.prepareStatement(QUERY_INSERT);
            ps.setInt(1,
                    Integer.parseInt("" + currentUser.getId() +gradeUser.getId()));
            ps.setInt(2, currentUser.getId());
            ps.setInt(3, gradeUser.getId());
            ps.setString(4, grade);
            ps.executeUpdate();
            ps.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<User> getLikedUser(User currentUser) {
        try {
            PreparedStatement ps = connection.prepareStatement(QUERY_GET_GRADE);
            ps.setInt(1, currentUser.getId());
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()){
                int id = Integer.parseInt(resultSet.getString("id"));
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String email = resultSet.getString("email");
                String photo = resultSet.getString("photo");
                String profession = resultSet.getString("profession");
                likedUsers.add(new User(id, name, surname, email, photo, profession));
            }
            ps.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return likedUsers;
    }

}
