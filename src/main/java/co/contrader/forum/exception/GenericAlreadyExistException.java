package co.contrader.forum.exception;

public class GenericAlreadyExistException extends RuntimeException{

    public GenericAlreadyExistException() {
        super("Element already Exist");
    }

}
