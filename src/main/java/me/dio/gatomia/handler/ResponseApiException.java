package me.dio.gatomia.handler;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public record ResponseApiException(String message, HttpStatus httpStatus, ZonedDateTime timestamp) {
}
