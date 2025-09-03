package com.wipro.sindhu.hms.exception;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.HashMap;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, String>> handleRuntime(RuntimeException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("timestamp", LocalDateTime.now().toString());
        error.put("error", ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

}
