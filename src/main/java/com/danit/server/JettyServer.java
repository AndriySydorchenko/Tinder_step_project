package com.danit.server;

import com.danit.controllers.HelloServlet;
import com.danit.controllers.LoginServlet;
import com.danit.controllers.UsersServlet;
import com.danit.controllers.WrongLogPassServlet;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHandler;

public class JettyServer {
    private final Server server =  new Server();
    public void start() throws Exception {
        ServerConnector connector = new ServerConnector(server);
        connector.setPort(8080);
        server.setConnectors(new Connector[]{connector});
//        ServletHandler handler = new ServletHandler();
        ServletContextHandler handler = new ServletContextHandler(ServletContextHandler.SESSIONS);
//        handler.addServletWithMapping(HelloServlet.class, "/hello");
//        handler.addServletWithMapping(LoginServlet.class, "/login");
//        handler.addServletWithMapping(WrongLogPassServlet.class, "/wrongLogPass");
//        handler.addServletWithMapping(UsersServlet.class, "/users");
        handler.addServlet(UsersServlet.class, "/users");

        server.setHandler(handler);
        server.start();
    }
    public void stop() throws Exception {
        server.stop();
    }
}
