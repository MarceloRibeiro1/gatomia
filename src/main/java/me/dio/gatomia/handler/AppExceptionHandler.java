package me.dio.gatomia.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(AppRequestException.class)
    public ResponseEntity<ResponseApiException> handleApiRequestException(AppRequestException ae) {
        ResponseApiException responseApiException = new ResponseApiException(ae.getMessage(), ae.getStatus(), ZonedDateTime.now());

        return new ResponseEntity<ResponseApiException>(responseApiException, responseApiException.getHttpStatus());
    }

    @ExceptionHandler({AppInvalidModelException.class, AppRepositoryException.class})
    public ResponseEntity<ResponseApiException> handleApiInvalidModelException(RuntimeException ae) {
        ResponseApiException responseApiException = new ResponseApiException(ae.getMessage(), HttpStatus.BAD_REQUEST, ZonedDateTime.now());

        return new ResponseEntity<ResponseApiException>(responseApiException, responseApiException.getHttpStatus());
    }
}
