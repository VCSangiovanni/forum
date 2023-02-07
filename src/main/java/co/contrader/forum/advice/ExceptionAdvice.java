package co.contrader.forum.advice;

import co.contrader.forum.exception.*;
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

    @ExceptionHandler(value = {WrongNameOrPass.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public String wrongCredential (WrongNameOrPass e) {
        return e.getMessage();
    }

    @ExceptionHandler(value = {GenericAlreadyExistException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public String genericAlreadyExist(GenericAlreadyExistException e) {
        return e.getMessage();
    }

    @ExceptionHandler(value = {GenericNotExistExceprion.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public String genericNotExist (GenericNotExistExceprion e) {
        return e.getMessage();
    }

    @ExceptionHandler(value = {BadCredentialException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public String badCredentialError (BadCredentialException e){
        return e.getMessage();
    }
}
