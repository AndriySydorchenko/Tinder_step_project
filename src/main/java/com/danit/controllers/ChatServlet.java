package com.danit.controllers;


import com.danit.model.Message;
import com.danit.model.User;
import com.danit.service.ChatServise;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.Version;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;


public class ChatServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        User currentUser = (User) session.getAttribute("currentUser");
        Configuration configuration = new Configuration(new Version("2.3.31"));
        configuration.setClassForTemplateLoading(LoginServlet.class, "/");
        configuration.setDefaultEncoding("UTF-8");
        Template template = configuration.getTemplate("chat.ftl");

        HashMap<String, Object> data = new HashMap<>();
        int currentChatId = Integer.parseInt(req.getParameter("chatId"));
        ChatServise chatServise = new ChatServise();
        List<Message> currentChatMessages = chatServise.getCurrentChatMessages(currentChatId);
        List<User> chatUsers = chatServise.getChatInterlocutors(currentChatId);

        data.put("messages",currentChatMessages);
        data.put("user",currentUser);
        data.put("chatId",currentChatId);

        for (User user : chatUsers){
            if (user.getId() != currentUser.getId()){
                data.put("participant",user);
            }
        }

        try {
            template.process(data ,resp.getWriter());
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }

}

