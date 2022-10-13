package me.dio.gatomia.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice
public class AppExceptionHandler {
    @ExceptionHandler({AppInvalidModelException.class})
    public ResponseEntity<ResponseApiException> handleApiInvalidModelException(AppInvalidModelException ae) {
        ResponseApiException responseApiException = new ResponseApiException(ae.getMessage(), HttpStatus.BAD_REQUEST, ZonedDateTime.now());

        return new ResponseEntity<>(responseApiException, responseApiException.httpStatus());
    }

    @ExceptionHandler({AppRepositoryException.class})
    public ResponseEntity<ResponseApiException> handleApiInvalidModelException(AppRepositoryException ae) {
        ResponseApiException responseApiException = new ResponseApiException(ae.getMessage(), HttpStatus.BAD_REQUEST, ZonedDateTime.now());

        return new ResponseEntity<>(responseApiException, responseApiException.httpStatus());
    }
}
