package web.server.handlers;

import web.server.domain.HttpResponse;
import web.server.socket.ServiceSocket;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

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
                socketService.sendResponse(HttpResponse.buildWithCode(200));
                return;
            }
            socketService.sendResponse(HttpResponse.buildWithCode(404));
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

