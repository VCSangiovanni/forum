package co.contrader.forum.exception;

public class UserNameAlreadyExistException extends RuntimeException{
    private final String userName;

    public UserNameAlreadyExistException(String userName){
        super("UserName already exist");
        this.userName = userName;
    }


}
