package com.danit.server;
import com.danit.controllers.*;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.util.resource.Resource;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

public class JettyServer {
    private final Server server =  new Server();
    public void start() throws Exception {
        ServerConnector connector = new ServerConnector(server);
        connector.setPort(8080);
        server.setConnectors(new Connector[]{connector});
        ServletContextHandler handler = new ServletContextHandler(ServletContextHandler.SESSIONS);

        handler.addServlet(StartPageServlet.class, "/");
        handler.addServlet(LoginServlet.class, "/login");
        handler.addServlet(WrongLogPassServlet.class, "/wrongLogPass");
        handler.addServlet(UsersServlet.class, "/users");
        handler.addServlet(LikedServlet.class, "/liked");

        handler.addFilter(AuthFilter.class, "/", EnumSet.of(DispatcherType.REQUEST));
        handler.addFilter(AuthFilter.class, "/users", EnumSet.of(DispatcherType.REQUEST));
        handler.addFilter(AuthFilter.class, "/liked", EnumSet.of(DispatcherType.REQUEST));

        server.setHandler(handler);

        server.start();
    }
    public void stop() throws Exception {
        server.stop();
    }
}
