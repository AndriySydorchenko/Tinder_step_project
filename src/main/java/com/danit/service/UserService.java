package com.danit.service;

import com.danit.dao.UsersDao;
import com.danit.model.User;

import java.util.List;

public class UserService {
    private UsersDao usersDao;

    public UserService() {
        usersDao = new UsersDao();
    }

    public boolean isUserExist(String mail, String password) {
        return usersDao.isUserExist(mail, password);
    }

    public User getCurrentUser() {
        return usersDao.getCurrentUser();
    }

    public boolean isValidCookieKey(String userEmail, String key) {
        return usersDao.isValidCookieKey(userEmail, key);
    }

    public void setCookieKey(String userEmail, String key) {
        usersDao.setUserCookieKey(userEmail, key);
    }

    public List<User> getUsers(User currentUser){
        return usersDao.getUsers(currentUser);
    }
}
