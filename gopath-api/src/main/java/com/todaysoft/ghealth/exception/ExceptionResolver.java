package com.todaysoft.ghealth.exception;


import com.hsgene.restful.response.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ExceptionResolver
{
    private static Logger log = LoggerFactory.getLogger(ExceptionResolver.class);

    
    @ExceptionHandler(value = ServiceException.class)
    public ResponseEntity<?> serviceExceptionHandler(ServiceException e)
    {
        log.error(e.getError());
        ErrorResponse response = new ErrorResponse();
        response.setError(e.getError());
        return new ResponseEntity<ErrorResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<?> defaultExceptionHandler(Exception e)
    {
        log.error(e.getMessage(), e);
        
        ErrorResponse response = new ErrorResponse();
        response.setError(ErrorCode.UNDEFINED_ERROR);
        return new ResponseEntity<ErrorResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
