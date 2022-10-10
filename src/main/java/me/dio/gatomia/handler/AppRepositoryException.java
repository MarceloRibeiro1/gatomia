package me.dio.gatomia.handler;

public class AppRepositoryException extends RuntimeException {
    public AppRepositoryException(String message) {
        super(message);
    }

    public AppRepositoryException(String message, Throwable cause) {
        super(message, cause);
    }
}
