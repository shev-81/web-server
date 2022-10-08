package web.server.domain;

import java.util.List;

public class FactoryResponseHTTP implements FactoryResponse {

    HttpResponse response;

    public FactoryResponseHTTP() {
        this.response = new HttpResponse();
    }

    @Override
    public HttpResponse buildResponse(int code){
        switch (code){
            case 200: {
                response.setData("")
                        .setHeaders(List.of("HTTP/1.1 200 OK", "Content-Type: text/html; charset=utf-8", "\n"));
                break;
            }
            case 404: {
                response.setData("<h1>Файл не найден!</h1>")
                        .setHeaders(List.of("HTTP/1.1 404 NOT_FOUND", "Content-Type: text/html; charset=utf-8", "\n"));
                break;
            }
            default:{
                throw new IllegalArgumentException("Wrong code response");
            }
        }
        return response;
    }
}
