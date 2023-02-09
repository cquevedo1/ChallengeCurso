package com.cristian_quevedo.challenge_quinto.excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.cristian_quevedo.challenge_quinto.modelo.response.ErrorResponse;

public class ExceptionCustomHandler {
    private static String messageDelete;
    private static String messageUpdate;

    public static ResponseEntity<?> throwError(HttpStatus status, String message) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(status.name())
                .code(status.value())
                .message(message)
                .build();

        return new ResponseEntity<>(errorResponse, status);
    }
    
    public static ResponseEntity<?> throwErrorEmail(HttpStatus status, String email) {
        messageUpdate = "El email " + email + " ya existe.";
        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(status.name())
                .code(status.value())
                .message(messageUpdate)
                .build();

        return new ResponseEntity<>(errorResponse, status);
    }
    
    public static ResponseEntity<?> throwErrorNotFound(HttpStatus status, String id) {
        messageDelete = "Id : " + id + " no existe.";
        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(status.name())
                .code(status.value())
                .message(messageDelete)
                .build();

        return new ResponseEntity<>(errorResponse, status);
    }
   
}
