package com.danit.controllers;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.http.HttpRequest;


@WebFilter(urlPatterns = {"/*"})
public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("do filter ....");
        chain.doFilter(request, response);
//        HttpServletRequest httpServletRequest = (HttpServletRequest)request;
//        HttpServletResponse httpServletResponse = (HttpServletResponse)response;
//
//        if(httpServletRequest.getSession() == null) {
//            httpServletResponse.sendRedirect("/login");
//        } else {
//            chain.doFilter(request, response);
//        }
    }

    @Override
    public void destroy() {

    }
}
