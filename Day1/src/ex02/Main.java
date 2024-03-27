package ex02;

public class Main {
    public static void main(String[] args) throws UserNotFoundException {
        User john = new User("John", 1000);
        User mike = new User("Mike", 1000);
        User ben = new User("Ben", 1000);


        UsersArrayList usersArrayList=new UsersArrayList();
        usersArrayList.addUser(john);
        System.out.println("Amount of users: "+usersArrayList.getAmountOfUsers());
        usersArrayList.addUser(mike);
        usersArrayList.addUser(ben);
        System.out.println("Amount of users: "+usersArrayList.getAmountOfUsers());

        System.out.println();

        System.out.println("getUserByIndex():");
        for (int i = 0; i < usersArrayList.getAmountOfUsers(); i++) {
            System.out.println("\tName of user " + usersArrayList.getUserByIndex(i).getName());
        }
        System.out.println();
        System.out.println("getUserById():");
        for (int i = 3; i >0; i--) {
            System.out.println("\tName of user " + usersArrayList.getUserByID(i).getName());
        }
        System.out.println();

    }
}
