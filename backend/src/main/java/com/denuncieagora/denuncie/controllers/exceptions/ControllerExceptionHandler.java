package com.denuncieagora.denuncie.controllers.exceptions;

import com.denuncieagora.denuncie.domain.exceptions.*;
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
        HttpStatus status = HttpStatus.NOT_FOUND;

        StandardError error = new StandardError();
        error.setTimestamp(Instant.now());
        error.setError("Report not found");
        error.setStatus(status.value());
        error.setMessage(e.getMessage());
        error.setPath(request.getRequestURI());

        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(IdentityInvalidException.class)
    public ResponseEntity<StandardError> identityIsInvalid(
            IdentityInvalidException e,
            HttpServletRequest request
    ) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        StandardError error = new StandardError();
        error.setTimestamp(Instant.now());
        error.setError("Identity is required");
        error.setStatus(status.value());
        error.setMessage(e.getMessage());
        error.setPath(request.getRequestURI());

        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(DescriptionInvalidException.class)
    public ResponseEntity<StandardError> descriptionIsInvalid(
            DescriptionInvalidException e,
            HttpServletRequest request
    ) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        StandardError error = new StandardError();
        error.setTimestamp(Instant.now());
        error.setError("Description is required");
        error.setStatus(status.value());
        error.setMessage(e.getMessage());
        error.setPath(request.getRequestURI());

        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(DateInvalidException.class)
    public ResponseEntity<StandardError> dateIsInvalid(
            DateInvalidException e,
            HttpServletRequest request
    ) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        StandardError error = new StandardError();
        error.setTimestamp(Instant.now());
        error.setError("Date is required");
        error.setStatus(status.value());
        error.setMessage(e.getMessage());
        error.setPath(request.getRequestURI());

        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(StateInvalidException.class)
    public ResponseEntity<StandardError> stateIsInvalid(
            StateInvalidException e,
            HttpServletRequest request
    ) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        StandardError error = new StandardError();
        error.setTimestamp(Instant.now());
        error.setError("State is required");
        error.setStatus(status.value());
        error.setMessage(e.getMessage());
        error.setPath(request.getRequestURI());

        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(CityInvalidException.class)
    public ResponseEntity<StandardError> cityIsInvalid(
            CityInvalidException e,
            HttpServletRequest request
    ) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        StandardError error = new StandardError();
        error.setTimestamp(Instant.now());
        error.setError("City is required");
        error.setStatus(status.value());
        error.setMessage(e.getMessage());
        error.setPath(request.getRequestURI());

        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(AboutInvalidException.class)
    public ResponseEntity<StandardError> aboutIsInvalid(
            AboutInvalidException e,
            HttpServletRequest request
    ) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        StandardError error = new StandardError();
        error.setTimestamp(Instant.now());
        error.setError("About is required");
        error.setStatus(status.value());
        error.setMessage(e.getMessage());
        error.setPath(request.getRequestURI());

        return ResponseEntity.status(status).body(error);
    }
}
