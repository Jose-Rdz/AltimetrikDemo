package com.altimetrik.demo;

import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.altimetrik.demo.util.ApiError;

/**
 *
 * @author G13380
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    
	@ExceptionHandler({Exception.class})
	@ResponseBody
    public ResponseEntity<ApiError> handleAll(Exception ex, WebRequest request) {
        ApiError apiError = new ApiError();
        apiError.setStatus("500");
        apiError.setTitle(HttpStatus.INTERNAL_SERVER_ERROR.toString());
        apiError.setError("Something went wrong...");
        apiError.setTimestamp(LocalDateTime.now());
        
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        
        return new ResponseEntity<>(apiError, httpHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
}
