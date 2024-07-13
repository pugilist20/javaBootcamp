package ex05.User;

public class UserNotFoundException extends Exception{
    public UserNotFoundException(){
        super("User not found");
    }
}
