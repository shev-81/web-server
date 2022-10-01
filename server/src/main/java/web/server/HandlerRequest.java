package web.server;

/**
 * Интерфейс слушателей запросов
 */
public interface HandlerRequest extends Runnable{
    void handle();
}
