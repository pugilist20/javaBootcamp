package ex02;

import java.util.Objects;

public class UsersArrayList implements UsersList{
    private int capacityUsers=10;
    private int amountOfUsers=0;
    private User[] users=new User[capacityUsers];
    @Override
    public void addUser(User user) {
        if(amountOfUsers==capacityUsers){
            capacityUsers*=(capacityUsers/2);
            User[] temp=new User[capacityUsers];
            if (amountOfUsers >= 0) System.arraycopy(users, 0, temp, 0, amountOfUsers);
            users=temp;
        }
        users[amountOfUsers++]=user;
    }

    @Override
    public User getUserByID(Integer ID) throws UserNotFoundException {
        for (int i=0;i<amountOfUsers;i++) {
            if(Objects.equals(users[i].getIdentifier(), ID)){
                return users[i];
            }
        }
        throw new UserNotFoundException();
    }

    @Override
    public User getUserByIndex(Integer index) {
        if(index<=amountOfUsers){
            return users[index];
        }
        return null;
    }

    public int getAmountOfUsers() {
        return amountOfUsers;
    }
}
