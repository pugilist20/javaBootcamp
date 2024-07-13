package edu.school21.chat.repositories;

import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;

import java.sql.SQLException;
import java.util.Optional;

public interface MessagesRepository {
    Optional<Message> findById(Long id) throws SQLException;

    public User findUserById(int id) throws SQLException;

    Chatroom findChatroomById(int id) throws SQLException;

    Chatroom saveMessage(Message message) throws SQLException;

    void checkMessage(Message message) throws SQLException;

}
