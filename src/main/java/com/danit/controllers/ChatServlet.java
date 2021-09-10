package com.danit.controllers;

import com.danit.dao.GradeDao;
import com.danit.dao.UsersDao;
import com.danit.model.User;
import com.danit.service.GradeService;
import com.danit.service.UserService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.Version;
import lombok.SneakyThrows;

import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ChatServlet extends HttpServlet {
    private int indexUser = 0;
    List<User> users = new ArrayList<>(0);

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Object currentUser = session.getAttribute("currentUser");
        System.out.println(req.getContextPath());
        Configuration configuration = new Configuration(new Version("2.3.31"));
        configuration.setClassForTemplateLoading(LoginServlet.class, "/");
        configuration.setDefaultEncoding("UTF-8");
        Template template = configuration.getTemplate("chat.ftl");
//        try {
//            if( users.size() == 0){
//                users = new UserService().getUsers((User) currentUser);
//            }
//            template.process(users.get(indexUser), resp.getWriter());
//        } catch (TemplateException e) {
//            e.printStackTrace();
//        }
        template.process(null,resp.getWriter());
    }

}

