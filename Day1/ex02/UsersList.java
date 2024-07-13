package ex02;

public interface UsersList {
    public void addUser(User user);
    public User getUserByID(Integer ID) throws UserNotFoundException;
    public User getUserByIndex(Integer index);
}
