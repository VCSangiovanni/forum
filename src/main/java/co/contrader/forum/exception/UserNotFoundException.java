package co.contrader.forum.exception;

import co.contrader.forum.model.User;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(){
        super("User not found");
    }

}
