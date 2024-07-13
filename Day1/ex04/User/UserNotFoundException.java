package ex04.User;

public class UserNotFoundException extends Exception{
    public UserNotFoundException(){
        super("User not found");
    }
}
