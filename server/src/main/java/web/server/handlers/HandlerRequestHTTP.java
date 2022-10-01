package web.server.handlers;

import web.server.domain.HttpResponse;
import web.server.socket.ServiceSocket;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class HandlerRequestHTTP implements HandlerRequest, Runnable {

    private ServiceSocket socketService;

    public HandlerRequestHTTP(ServiceSocket socketService) throws IOException {
        this.socketService = socketService;
    }

    @Override
    public void handle() {
        try {
            Path path = socketService.readRequest();
            if (!Files.exists(path)) {
                socketService.sendResponse(new HttpResponse(List.of("HTTP/1.1 404 NOT_FOUND", "Content-Type: text/html; charset=utf-8", "\n"), "<h1>Файл не найден!</h1>"));
                return;
            }
            socketService.sendResponse(new HttpResponse(List.of("HTTP/1.1 200 OK", "Content-Type: text/html; charset=utf-8", "\n"), ""));
            Files.newBufferedReader(path).transferTo(socketService.getOutput());
            socketService.halt();
            System.out.println("Client disconnected!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        handle();
    }
}

