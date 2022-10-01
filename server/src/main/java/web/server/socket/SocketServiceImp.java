package web.server.socket;

import web.server.domain.HttpRequest;
import web.server.domain.HttpResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class SocketServiceImp implements ServiceSocket {

    private String WWW;
    private Socket socket;
    private BufferedReader input;
    private PrintWriter output;


    public SocketServiceImp(String WWW, Socket socket) throws IOException {
        this.WWW = WWW;
        this.socket = socket;
        this.input = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
        this.output = new PrintWriter(socket.getOutputStream());
    }

    @Override
    public void sendResponse(HttpResponse response){
        output.println(response.parse());
        output.flush();
    }

    @Override
    public void halt() {
        try {
            input.close();
            output.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public PrintWriter getOutput() {
        return output;
    }

    @Override
    public Path readRequest() {
        try {
            while (!input.ready());
            List<String> request = new ArrayList<>();
            while (input.ready()) {
                String line = input.readLine();
                request.add(line);
            }
            HttpRequest requestTmp = new HttpRequest(request, WWW);
            return requestTmp.deserialize();
        } catch (IOException ex) {
            throw new IllegalStateException(ex);
        }
    }
}
