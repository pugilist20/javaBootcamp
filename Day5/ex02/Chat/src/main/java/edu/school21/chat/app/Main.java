package edu.school21.chat.app;

import edu.school21.chat.models.Message;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;

import java.sql.SQLException;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) throws SQLException {
        MessagesRepositoryJdbcImpl messagesRepositoryJdbc=new MessagesRepositoryJdbcImpl(DBConnector.getConnection());
        Message message = new Message(null, messagesRepositoryJdbc.findUserById(1),
                messagesRepositoryJdbc.findChatroomById(2), "Hello world!", LocalDateTime.now());
        messagesRepositoryJdbc.saveMessage(message);

        System.out.println("id = " + message.getId());
    }
}
