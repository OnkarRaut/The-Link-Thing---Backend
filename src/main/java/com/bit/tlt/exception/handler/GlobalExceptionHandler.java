package com.bit.tlt.exception.handler;

import com.bit.tlt.model.TltResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.lang.reflect.MalformedParametersException;

@Log4j2
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<TltResponse<Void>> handleBadCredentialException(BadCredentialsException exception, WebRequest webRequest) {
       return ResponseEntity.badRequest()
           .body(
               TltResponse.<Void>builder()
                   .httpStatus(HttpStatus.BAD_REQUEST)
                   .httpStatusCode(HttpStatus.BAD_REQUEST.value())
                   .message("Invalid username or password")
                   .build()
           );
    }

    @ExceptionHandler(MalformedParametersException.class)
    public ResponseEntity<TltResponse<Void>> handleMalformedException(MalformedParametersException exception, WebRequest webRequest) {
        return ResponseEntity.badRequest()
            .body(
                TltResponse.<Void>builder()
                    .httpStatus(HttpStatus.BAD_REQUEST)
                    .httpStatusCode(HttpStatus.BAD_REQUEST.value())
                    .message(exception.getMessage())
                    .build()
            );
    }

}
