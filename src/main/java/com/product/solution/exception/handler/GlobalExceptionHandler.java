package com.product.solution.exception.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.product.solution.exception.NoRecordFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, List<String>> body = new HashMap<>();

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        body.put("errors", errors);
        logger.error("{}",errors, ex);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> uniqueConstraintViolation(DataIntegrityViolationException exception){
        Map<String, List<String>> body = new HashMap<>();
        List<String> errors  = new ArrayList<>();
        errors.add("duplicate SKU not allowed");
        body.put("errors", errors);
        logger.error("{}",errors, exception);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoRecordFoundException.class)
    public ResponseEntity<Object> noRecordFound(NoRecordFoundException exception){
        Map<String, List<String>> body = new HashMap<>();
        List<String> errors  = new ArrayList<>();
        errors.add(exception.getMessage());
        body.put("errors", errors);
        logger.error("{}",errors, exception);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> genericError(Exception exception){
        Map<String, List<String>> body = new HashMap<>();
        List<String> errors  = new ArrayList<>();
        errors.add("Error while processing request");
        body.put("errors", errors);
        logger.error("{}",errors, exception);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}

