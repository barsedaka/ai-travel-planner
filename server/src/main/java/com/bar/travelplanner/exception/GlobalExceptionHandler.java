package com.bar.travelplanner.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = UsernameAlreadyExistsException.class)
    @ResponseBody
    public ResponseEntity<ErrorDetails> handleException(UsernameAlreadyExistsException exception) {

        return new ResponseEntity<>(
                new ErrorDetails(exception.getStatus().name(),
                        exception.getStatus().value(),
                        exception.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = EmailAlreadyExistsException.class)
    @ResponseBody
    public ResponseEntity<ErrorDetails> handleException(EmailAlreadyExistsException exception) {
        return new ResponseEntity<>(
                new ErrorDetails(exception.getStatus().name(),
                        exception.getStatus().value(),
                        exception.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<ErrorDetails> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getAllErrors()
                .stream()
                .map(error -> {
                    String fieldName = ((FieldError) error).getField();
                    String errorMessage = error.getDefaultMessage();
                    return fieldName + ": " + errorMessage;
                })
                .collect(Collectors.toList());

        return new ResponseEntity<>(
                new ErrorDetails(HttpStatus.BAD_REQUEST.name(),
                    HttpStatus.BAD_REQUEST.value(),
                    String.join("; ", errors)),
                HttpStatus.BAD_REQUEST);

    }
}
