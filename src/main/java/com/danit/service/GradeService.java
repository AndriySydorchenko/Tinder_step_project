package com.danit.service;

import com.danit.dao.GradeDao;
import com.danit.model.User;

import java.util.List;


public class GradeService {
    private GradeDao gradeDao;

    public GradeService() {
        this.gradeDao = new GradeDao();
    }

    public List<User> getLikedUsers(User currentUser){
        return gradeDao.getLikedUser(currentUser);
    }
    public void insertGrade(User currentUser, User likedUser, String grade){
        gradeDao.insertGrade(currentUser, likedUser, grade);
    }
}
