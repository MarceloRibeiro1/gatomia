package me.dio.gatomia.handler;

import org.springframework.http.HttpStatus;


public class AppRequestException extends RuntimeException {
    private static HttpStatus status = HttpStatus.BAD_REQUEST;

    public AppRequestException() {
    }

    public AppRequestException(String message) {
        super(message);
    }

    public AppRequestException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public AppRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public static HttpStatus getStatus() {
        return status;
    }

}
