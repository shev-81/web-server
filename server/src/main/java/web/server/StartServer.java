package web.server;

public class StartServer {

    private final static Servers server = new ServerHTTP();

    public static void main(String[] args) {
        server.up();
    }
}
