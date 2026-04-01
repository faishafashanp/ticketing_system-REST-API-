package com.take_home_test.tht.exception;

import com.take_home_test.tht.dto.WebResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class globalExeptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<WebResponse<String>> apiException(ResponseStatusException exception) {
        return ResponseEntity.status(exception.getStatusCode())
                .body(WebResponse.<String>builder()
                        .status("error")
                        .message(exception.getReason())
                        .build());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<WebResponse<String>> internalServerError(Exception exception) {
        return ResponseEntity.status(500)
                .body(WebResponse.<String>builder()
                        .status("error")
                        .message("Internal Server Error: " + exception.getMessage())
                        .build());
    }
}
