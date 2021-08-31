package com.danit.server;

import freemarker.template.Configuration;
import freemarker.template.Version;

public class Main {
    public static void main(String[] args) throws Exception {
        JettyServer jettyServer = new JettyServer();
        jettyServer.start();
    }
}
