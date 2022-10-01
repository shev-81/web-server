package web.server.socket;

import web.server.domain.HttpResponse;

import java.io.PrintWriter;
import java.nio.file.Path;

public interface ServiceSocket {

    void halt();
    void sendResponse (HttpResponse response);
    PrintWriter getOutput();
    Path readRequest();

}
