package com.danit.server;
import javax.servlet.DispatcherType;

public class Main {
    public static void main(String[] args) throws Exception {
//        Thread.currentThread().setContextClassLoader(JettyServer.class.getClassLoader());
        JettyServer jettyServer = new JettyServer();
        jettyServer.start();
    }
}
