package pt.luis.blogapp.api.exceptions.Exceptions;

public class BadRequestException extends RuntimeException{

    public BadRequestException(String message){
        super(message);
    }
}
