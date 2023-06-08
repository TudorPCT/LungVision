package com.lungvision.lungdiseasexrayclassificationbe.exceptionHandler;

import com.lungvision.lungdiseasexrayclassificationbe.exception.*;
import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppExceptionHandler {

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<String> handleBadCredentialsException(BadCredentialsException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"message\": \"" + ex.getMessage() + "\"}");
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"message\": \"" + ex.getMessage() + "\"}");
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(JwtException.class)
    public ResponseEntity<String> handleJWTException(JwtException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"message\": \"" + ex.getMessage() + "\"}");
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<String> handleEmailAlreadyExistsException(EmailAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("{\"message\": \"" + ex.getMessage() + "\"}");
    }

    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    @ExceptionHandler(ServiceUnavailable.class)
    public ResponseEntity<String> handleServiceUnavailable(ServiceUnavailable ex) {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("{\"message\": \"" + ex.getMessage() + "\"}");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(PredictionNotFoundException.class)
    public ResponseEntity<String> handlePredictionNotFoundException(PredictionNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\": \"" + ex.getMessage() + "\"}");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationException(MethodArgumentNotValidException ex) {
        return ResponseEntity.badRequest().body("{\"message\": \"" + ex.getMessage() + "\"}");
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CloudinaryException.class)
    public ResponseEntity<String> handleCloudinaryException(CloudinaryException ex) {
        return ResponseEntity.badRequest().body("{\"message\": \"" + ex.getMessage() + "\"}");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\": \"" + ex.getMessage() + "\"}");
    }

}
