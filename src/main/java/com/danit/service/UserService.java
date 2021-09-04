package com.danit.service;

import com.danit.dao.UsersDao;
import com.danit.model.User;

public class UserService {
    UsersDao usersDao;

    public UserService() {
        usersDao = new UsersDao();
    }

    public boolean isUserExist(String mail, String password) {
        return usersDao.isUserExist(mail, password);
    }

    public User getCurrentUser() {
        return usersDao.getCurrentUser();
    }
}
