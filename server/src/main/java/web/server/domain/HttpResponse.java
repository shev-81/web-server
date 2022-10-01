package web.server.domain;

import java.util.List;
import java.util.stream.Collectors;

public class HttpResponse implements ParserResponse{

    private List<String> headers;
    private String data;

    public HttpResponse(List<String> headers, String data) {
        this.headers = headers;
        this.data = data;
    }

    @Override
    public String parse() {
        return headers.stream().map(l -> l + "\n").collect(Collectors.joining()) + "\n" + data + "\n";
    }
}
