package com.mapping.exception;

import com.mapping.payload.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException exception) {
        ErrorResponse response = new ErrorResponse();
        String message = exception.getMessage();
        response.setMessage(message);
        response.setSuccess(false);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(DuplicateEntry.class)
    public ResponseEntity<ErrorResponse> handleDuplicateEntryException(DuplicateEntry exception) {
        ErrorResponse response = new ErrorResponse();
        String message = exception.getMessage();
        response.setMessage(message);
        response.setSuccess(false);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

    }

}
