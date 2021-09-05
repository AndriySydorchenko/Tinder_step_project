package com.danit.controllers;

import com.danit.dao.UserDao;
import com.danit.models.User;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.Version;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;


public class UsersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        System.out.println(session.isNew());
        System.out.println(session.getId());

        Configuration configuration = new Configuration(new Version("2.3.31"));
        configuration.setClassForTemplateLoading(LoginServlet.class, "/");
        configuration.setDefaultEncoding("UTF-8");
        Template template = configuration.getTemplate("likePage.ftl");
        try {
            List<User> users = new UserDao().getUsers();
            template.process(users.get(0), resp.getWriter());
        } catch (SQLException | TemplateException e) {
            e.printStackTrace();
        }
//        System.out.println(req.getSession().getAttribute("currentUser"));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        String path = "/users";
        if(action.equals("like")) {
            System.out.println("like");
        } else if(action.equals("dislike")){
            System.out.println("dislike");
        }
        resp.sendRedirect(path);
    }
}
