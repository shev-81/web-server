package web.server;

import web.server.servers.ServerHTTP;
import web.server.servers.Servers;

public class StartServer {

    private final static Servers server = new ServerHTTP();

    public static void main(String[] args) {
        server.up();
    }
}
