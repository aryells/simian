package com.mercadolivre.simian.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.mercadolivre.simian.exception.NotSimianException;
import com.mercadolivre.simian.exception.NotValidDNAException;

@ControllerAdvice
public class ResponseExceptionHandler {
    @ExceptionHandler({NotSimianException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    public ResponseEntity<Throwable> handleNotSimianException(Exception e) {
        return new ResponseEntity(e.getCause(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler({NotValidDNAException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<Throwable>  handleNotValidDNAException(Exception e) {
        return new ResponseEntity(e.getCause(), HttpStatus.BAD_REQUEST);
    }
}