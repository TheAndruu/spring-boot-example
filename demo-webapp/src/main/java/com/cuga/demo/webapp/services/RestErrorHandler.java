package com.cuga.demo.webapp.services;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cuga.demo.core.dto.ValueDto;

@RestController
@ControllerAdvice()
public class RestErrorHandler {

    private final static Logger log = LoggerFactory.getLogger(RestErrorHandler.class);

    @ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
    // The type of exceptions we want this class to handle
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    public ValueDto<String> handleRuntimeException(HttpServletRequest req, Exception ex) {
        log.error("Illegal argument handled: " + ex.getMessage());
        return new ValueDto<>(ex.getMessage());
    }
}
