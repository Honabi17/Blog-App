package pt.luis.blogapp.api.exceptions;

import java.time.LocalDateTime;

public record ApiErrorResponse(
        String message,
        LocalDateTime timestamp
) {

    public ApiErrorResponse(String message){
        this(message, LocalDateTime.now());
    }
}
