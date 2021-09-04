package com.danit.server;
import com.danit.controllers.*;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletHandler;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

public class JettyServer {
    private final Server server =  new Server();
    public void start() throws Exception {
        ServerConnector connector = new ServerConnector(server);
        connector.setPort(8080);

        server.setConnectors(new Connector[]{connector});
        ServletHandler handler = new ServletHandler();
        handler.addServletWithMapping(LoginServlet.class, "/login");
        handler.addServletWithMapping(WrongLogPassServlet.class, "/wrongLogPass");
        handler.addServletWithMapping(UsersServlet.class, "/users");

        handler.addFilterWithMapping(AuthFilter.class, "/*", EnumSet.of(DispatcherType.REQUEST));
        server.setHandler(handler);
        server.start();
    }
    public void stop() throws Exception {
        server.stop();
    }
}
