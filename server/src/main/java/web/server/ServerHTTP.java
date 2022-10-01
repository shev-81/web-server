package web.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerHTTP implements Servers{

    private static String WWW;
    private static int PORT;


    ServerHTTP(){
        WWW = "C:\\Users\\Andrey\\IdeaProjects\\web-server\\www\\index.html";
        PORT = 8080;
    }

    @Override
    public void up() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server started!");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected!");
                HandlerRequest handler = new HandlerRequestHTTP(socket, WWW);
                new Thread(handler).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
