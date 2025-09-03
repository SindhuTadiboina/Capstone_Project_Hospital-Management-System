package com.wipro.sindhu.hms.exceptions;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.HashMap;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	 @ExceptionHandler(ResourceNotFoundException.class)
	    public ResponseEntity<?> handleResourceNotFound(ResourceNotFoundException ex) {
	        Map<String, Object> body = new HashMap<>();
	        body.put("timestamp", LocalDateTime.now());
	        body.put("message", ex.getMessage());
	        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
	    }

}
