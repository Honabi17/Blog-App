package pt.luis.blogapp.api.exceptions.Exceptions;

public class ResourceAlreadyExistsException  extends RuntimeException{
    public ResourceAlreadyExistsException(String message){
        super(message);
    }
}
