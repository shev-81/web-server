package web.server.domain;

import java.util.List;
import java.util.stream.Collectors;

public class HttpResponse implements ParserResponse {

    private List<String> headers;
    private String data;
    private final static FactoryResponse factoryResponse = new FactoryResponseHTTP();

    public HttpResponse() {
    }

    public HttpResponse setHeaders(List<String> headers) {
        this.headers = headers;
        return this;
    }

    public HttpResponse setData(String data) {
        this.data = data;
        return this;
    }

    @Override
    public String parse() {
        return headers.stream().map(l -> l + "\n").collect(Collectors.joining()) + "\n" + data + "\n";
    }

    public static HttpResponse buildWithCode(int statusCode) {
        return factoryResponse.buildResponse(statusCode);
    }
}
