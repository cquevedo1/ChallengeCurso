package com.CristianQuevedo.ChallengeQuinto.modelo.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponse {
    
    private String status;
    
    private int code;
    
    private String message;
}
