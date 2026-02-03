package com.sanketttt26.tickets.controllers;

import com.sanketttt26.tickets.domain.dtos.ErrorDTO;
import com.sanketttt26.tickets.exceptions.UserNotFoundException;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleUserNotFoundException(RuntimeException ex) {
        log.error("An error occurred: User Not Found ", ex);
        ErrorDTO errorDTO = new ErrorDTO("User not found.");
        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        log.error("An error occurred: Method Argument Not Valid ", ex);
        ErrorDTO errorDTO = new ErrorDTO();

        String errorMessage = ex.getBindingResult().getFieldErrors().stream().
                findFirst()
                .map(fieldError -> fieldError.getField() + " " + fieldError.getDefaultMessage())
                .orElse("Validation error");
        errorDTO.setError(errorMessage);
        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorDTO> handleConstraintViolationException(ConstraintViolationException ex) {
        log.error("An error occurred:Constraint Violation ", ex);
        ErrorDTO errorDTO = new ErrorDTO();

        String errorMessage = ex.getConstraintViolations().stream().findFirst().map(violation->violation.getPropertyPath()+" "+violation.getMessage())
                .orElse("Constraint violation error");
        errorDTO.setError(errorMessage);
        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> handleException(Exception ex) {
        log.error("An error occurred: ", ex);
        ErrorDTO errorDTO = new ErrorDTO("An unexpected error occurred. Please try again later.");
        return new ResponseEntity<>(errorDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
