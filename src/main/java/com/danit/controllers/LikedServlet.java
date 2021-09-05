package com.danit.controllers;

import com.danit.dao.UserDao;
import com.danit.models.User;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.Version;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class LikedServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Configuration configuration = new Configuration(new Version("2.3.31"));
        configuration.setClassForTemplateLoading(LikedServlet.class, "/");
        configuration.setDefaultEncoding("UTF-8");
        Template template = configuration.getTemplate("peopleList.ftl");
        try {
            List<User> users = new UserDao().getUsers();
            template.process(users.get(0), resp.getWriter());
        } catch (SQLException | TemplateException e) {
            e.printStackTrace();
        }
    }
}
