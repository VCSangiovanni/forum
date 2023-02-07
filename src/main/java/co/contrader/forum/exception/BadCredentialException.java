package co.contrader.forum.exception;

public class BadCredentialException extends RuntimeException{

    public BadCredentialException() {
        super("You do not have permission for this request");
    }

}
