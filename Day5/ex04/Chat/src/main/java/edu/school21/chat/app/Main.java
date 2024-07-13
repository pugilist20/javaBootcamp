package edu.school21.chat.app;

import edu.school21.chat.repositories.UsersRepositoryJdbcImpl;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        UsersRepositoryJdbcImpl usersRepositoryJdbc=new UsersRepositoryJdbcImpl(DBConnector.getConnection());
        System.out.println(usersRepositoryJdbc.findAll(0,5).toString());
    }
}
