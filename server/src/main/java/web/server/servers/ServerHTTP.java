package web.server.servers;

import web.server.handlers.HandlerRequestHTTP;
import web.server.socket.SocketServiceImp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerHTTP implements Servers{

    private static String WWW;
    private static int PORT;


    public ServerHTTP(){
        WWW = "/www";
        PORT = 8088;
    }

    @Override
    public void up() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server started!");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected!");
                new Thread(new HandlerRequestHTTP(new SocketServiceImp(WWW , socket))).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
