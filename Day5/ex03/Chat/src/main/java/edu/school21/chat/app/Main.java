package edu.school21.chat.app;

import edu.school21.chat.models.Message;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        MessagesRepositoryJdbcImpl messagesRepositoryJdbc=new MessagesRepositoryJdbcImpl(DBConnector.getConnection());
        Message message;
        if(messagesRepositoryJdbc.findById(2L).isPresent()) {
            message = messagesRepositoryJdbc.findById(2L).get();
            message.setCreator(messagesRepositoryJdbc.findUserById(4));
            message.setText("Hi");
            messagesRepositoryJdbc.update(message);
        }


    }
}
