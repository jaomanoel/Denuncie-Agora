package com.denuncieagora.denuncie.domain.exceptions;

import com.denuncieagora.denuncie.controllers.exceptions.StandardError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ReportNotFoundException.class)
    public ResponseEntity<StandardError> reportNotFound(
            ReportNotFoundException e,
            HttpServletRequest request
    ) {
        StandardError error = new StandardError();
        error.setTimestamp(Instant.now());
        error.setError("Report not found");
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(e.getMessage());
        error.setPath(request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}
