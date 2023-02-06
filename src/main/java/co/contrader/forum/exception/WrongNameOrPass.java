package co.contrader.forum.exception;

public class WrongNameOrPass extends RuntimeException{
    public WrongNameOrPass(){
        super("Wrong Username or Password");
    }
}
