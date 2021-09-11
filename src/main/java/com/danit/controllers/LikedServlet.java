package com.danit.controllers;

import com.danit.dao.GradeDao;
import com.danit.dao.UsersDao;
import com.danit.model.User;
import com.danit.service.GradeService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.Version;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class LikedServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Object currentUser = session.getAttribute("currentUser");
        Configuration configuration = new Configuration(new Version("2.3.31"));
        configuration.setClassForTemplateLoading(LikedServlet.class, "/");
        configuration.setDefaultEncoding("UTF-8");
        Template template = configuration.getTemplate("peopleList.ftl");
        try {
            List<User> likedUser = new GradeService().getLikedUsers((User) currentUser);
//            Map<String, Object> values = Map.of("users", likedUser);
            Map<String, Object> values =  new HashMap<String, Object>();
            values.put("users", likedUser);
            template.process(values, resp.getWriter());
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Object currentUser = session.getAttribute("currentUser");
        resp.sendRedirect("/messages/"+ req.getParameter("id"));
    }
}
