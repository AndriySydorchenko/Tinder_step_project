package com.danit.controllers;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.Version;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class StartPageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Configuration configuration = new Configuration(new Version("2.3.31"));
        configuration.setClassForTemplateLoading(StartPageServlet.class, "/");
        configuration.setDefaultEncoding("UTF-8");
        Template template = configuration.getTemplate("startPage.html");
        try {
            template.process(null, resp.getWriter());
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }
}

