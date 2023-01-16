package com.CristianQuevedo.ChallengeQuinto.excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.CristianQuevedo.ChallengeQuinto.modelo.response.ErrorResponse;


public class ExceptionCustomHandler {
    private static String messageDelete;
   
    /**
     * Excepcion id no encontrada
     * @param status
     * @param id
     * @return
     */
     public static ResponseEntity<?> throwErrorNotFound(HttpStatus status, String id) {
        messageDelete = "Id : " + id + " no existe.";
        ErrorResponse errorResponse = ErrorResponse.builder()
                .code(status.value())
                .message(messageDelete)
                .build();
        return new ResponseEntity<>(errorResponse, status);
    }

    /**
     * Excepcion error en respuesta
     * @param status
     * @param message
     * @return
     */
    public static ResponseEntity<?> throwError(HttpStatus status, String message) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(status.name())
                .code(status.value())
                .message(message)
                .build();
        return new ResponseEntity<>(errorResponse, status);
    }
}
