package com.cristian_quevedo.challenge_quinto.dominio.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponse {
    
    private String status;
    
    private int code;
    
    private String message;
}
