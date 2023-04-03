package com.visma.loan.calculator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestErrorHandler {

    private final static String ERRORS_KEY = "errors";

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception exception) {
        Map<String, Object> body = buildErrorMap(HttpStatus.INTERNAL_SERVER_ERROR);
        String errorMessage = exception.getMessage();
        body.put(ERRORS_KEY, errorMessage);
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception) {
        Map<String, Object> body = buildErrorMap(HttpStatus.BAD_REQUEST);
        List<String> errors = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());
        body.put(ERRORS_KEY, errors);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException exception) {
        Map<String, Object> body = buildErrorMap(HttpStatus.BAD_REQUEST);
        String errorMessage = exception.getCause().getMessage();
        body.put(ERRORS_KEY, errorMessage);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    private static Map<String, Object> buildErrorMap(HttpStatus status) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDate.now());
        body.put("status", status.value());
        return body;
    }
}