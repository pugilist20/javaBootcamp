package edu.school21.chat.app;

import edu.school21.chat.models.User;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<User> users= CRUDUtils.getUserData("select * from chat.users");
        System.out.println(users);

    }
}
