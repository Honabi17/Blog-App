package pt.luis.blogapp.api.exceptions.Exceptions;

public class AccessDeniedException extends RuntimeException{
    public AccessDeniedException(String message){
        super (message);
    }
}
