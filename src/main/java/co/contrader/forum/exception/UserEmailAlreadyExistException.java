package co.contrader.forum.exception;

public class UserEmailAlreadyExistException extends RuntimeException{

    private final String userEmail;

    public UserEmailAlreadyExistException(String userEmail){
        super("User Email already exist");
        this.userEmail = userEmail;
    }

}
