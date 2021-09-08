package com.danit.controllers;

import com.danit.model.User;
import com.danit.service.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthFilter implements Filter {

    private UserService userService = new UserService();
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("do filter");
        HttpServletRequest httpServletRequest = (HttpServletRequest)request;
        HttpServletResponse httpServletResponse = (HttpServletResponse)response;
        HttpSession session = httpServletRequest.getSession();

        if (session.getAttribute("currentUser") == null) {
            Cookie[] cookies = httpServletRequest.getCookies();
            if (cookies != null) {
                String userEmail = "";
                String cookie_key = "";
                for (Cookie c: cookies) {
                    if(c.getName().equals("login")) {
                        userEmail = c.getValue();
                    } else if (c.getName().equals("key")) {
                        cookie_key = c.getValue();
                    }
                }
                if (userEmail != "" && cookie_key != "") {
                    if (userService.isValidCookieKey(userEmail, cookie_key)) {
                       chain.doFilter(request, response);
                    } else {
                        httpServletResponse.sendRedirect("/login");
                    };
                } else {
                    httpServletResponse.sendRedirect("/login");
                }
            } else {
                httpServletResponse.sendRedirect("/login");
            }
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {}
}
