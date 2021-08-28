package com.danit.server;

import com.danit.controllers.HelloServlet;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletHandler;

public class JettyServer {
    private final Server server =  new Server();
    public void start() throws Exception {
        ServerConnector connector = new ServerConnector(server);
        connector.setPort(8080);
        server.setConnectors(new Connector[]{connector});
        ServletHandler handler = new ServletHandler();
        handler.addServletWithMapping(HelloServlet.class, "/hello");
        server.setHandler(handler);
        server.start();
    }
    public void stop() throws Exception {
        server.stop();
    }
}
