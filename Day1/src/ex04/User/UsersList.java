package ex04.User;


public interface UsersList {
    public void addUser(User user);
    public User getUserByID(Integer ID) throws UserNotFoundException;
    public User getUserByIndex(Integer index);
}
