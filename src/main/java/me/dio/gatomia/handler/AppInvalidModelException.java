package me.dio.gatomia.handler;

public class AppInvalidModelException extends RuntimeException {
    public AppInvalidModelException(String message) {
        super(message);
    }

    public AppInvalidModelException(String message, Throwable cause) {
        super(message, cause);
    }

    public AppInvalidModelException() {
    }
}
