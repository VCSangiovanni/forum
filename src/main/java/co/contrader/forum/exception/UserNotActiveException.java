package co.contrader.forum.exception;

public class UserNotActiveException extends RuntimeException{

    public UserNotActiveException(){
        super("User not Active");
    }

}
