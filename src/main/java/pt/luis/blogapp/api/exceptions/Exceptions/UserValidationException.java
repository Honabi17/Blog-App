package pt.luis.blogapp.api.exceptions.Exceptions;

public class UserValidationException extends RuntimeException {
    public UserValidationException(String message){
        super (message);
    }
}
