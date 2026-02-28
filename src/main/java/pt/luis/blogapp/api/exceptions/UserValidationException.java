package pt.luis.blogapp.api.exceptions;

public class UserValidationException extends RuntimeException {
    public UserValidationException(String message){
        super (message);
    }
}
