package com.danit.controllers;

import com.danit.server.Main;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.Version;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Configuration configuration = new Configuration(new Version("2.3.31"));
        configuration.setClassForTemplateLoading(LoginServlet.class, "/");
        configuration.setDefaultEncoding("UTF-8");
        Template template = configuration.getTemplate("login.ftl");
        try {
            template.process(null, resp.getWriter());
        } catch (TemplateException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HashMap<String, String> accounts = new HashMap<>();
        accounts.put("test@mail.com", "test");
        accounts.put("tinder@mail.com", "tinder");

        String inputEmail = req.getParameter("inputEmail");
        String inputPassword = req.getParameter("inputPassword");

        if(accounts.containsKey(inputEmail) && accounts.get(inputEmail).equals(inputPassword)) {
           resp.sendRedirect("/hello");
        } else {
            resp.sendRedirect("/wrongLogPass");
        }
    }
}
