package co.contrader.forum.advice;

import co.contrader.forum.exception.UserEmailAlreadyExistException;
import co.contrader.forum.exception.UserNameAlreadyExistException;
import co.contrader.forum.exception.UserNotActiveException;
import co.contrader.forum.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * this class capture and handle the custom exception
 */

@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(value = {UserNameAlreadyExistException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public String userNameError(UserNameAlreadyExistException e){
        return e.getMessage();
    }

    @ExceptionHandler(value = {UserEmailAlreadyExistException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public String userEmailError(UserEmailAlreadyExistException e){
        return e.getMessage();
    }

    @ExceptionHandler(value = {UserNotFoundException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public String userNotFound(UserNotFoundException e){
        return e.getMessage();
    }

    @ExceptionHandler(value = {UserNotActiveException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public String userNotActive(UserNotActiveException e){
        return e.getMessage();
    }
}
