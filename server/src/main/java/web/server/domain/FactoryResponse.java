package web.server.domain;

public interface FactoryResponse {
    HttpResponse buildResponse(int code);
}
