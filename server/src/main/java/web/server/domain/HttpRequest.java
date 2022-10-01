package web.server.domain;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class HttpRequest implements RequestDeserializer {

    private List<String> request;
    private String WWW;

    public HttpRequest(List<String> request, String WWW) {
        this.request = request;
        this.WWW = WWW;
    }

    @Override
    public Path deserialize() {
        String[] parts = request.get(0).split(" ");
        return Paths.get(WWW, parts[1]);
    }
}
