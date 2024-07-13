package edu.school21.chat.repositories;

import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;

public class MessagesRepositoryJdbcImpl implements MessagesRepository {
    Connection connection;

    public MessagesRepositoryJdbcImpl(DataSource dataSource) throws SQLException {
        connection = dataSource.getConnection();
    }

    @Override
    public Optional<Message> findById(Long id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from chat.messages where id=?");
        preparedStatement.setLong(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int messageID = resultSet.getInt("id");
            int authorID = resultSet.getInt("authorid");
            int chatroomID = resultSet.getInt("chatroomid");
            String text = resultSet.getString("text");
            String datetime = resultSet.getString("datetime").replace('-', '/').substring(2, 16);
            System.out.println("Message : {\n id=" + messageID + ",\n " + findUserById( authorID) + ",\n " + findChatroomById( chatroomID) + ",\n text=\"" + text + "\",\n datetime=" + datetime + "\n}");
        }
        return Optional.empty();
    }

    public User findUserById(int id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from chat.users where id=?");
        preparedStatement.setLong(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int userID = resultSet.getInt("id");
            String login = resultSet.getString("login");
            String password = resultSet.getString("password");
            return new User(userID, login, password, new ArrayList<>(), new ArrayList<>());
        }
        return null;
    }

    public Chatroom findChatroomById(int id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from chat.chatrooms where id=?");
        preparedStatement.setLong(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int chatroomID = resultSet.getInt("id");
            String name = resultSet.getString("name");
            int ownerID = resultSet.getInt("ownerID");
            return new Chatroom(chatroomID, name, ownerID, new ArrayList<>());
        }
        return null;
    }

    public Chatroom saveMessage(Message message) throws SQLException {
        checkMessage(message);
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO chat.messages (authorID, chatroomID, text, datetime) VALUES (?, ?, ?, ?) returning id");
        preparedStatement.setInt(1, message.getCreator().getId());
        preparedStatement.setInt(2, message.getChatroom().getId());
        preparedStatement.setString(3, message.getText());
        preparedStatement.setTimestamp(4, Timestamp.valueOf(message.getDateTime()));
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()) {
            message.setId(resultSet.getInt(1));
        }
        return null;
    }
    public void checkMessage(Message message) throws SQLException {
        if (message.getCreator() == null || findUserById(message.getCreator().getId()) == null) {
            throw new NotSavedSubEntityException("Non existing user id");
        }
        if (message.getChatroom() == null || findChatroomById(message.getChatroom().getId()) == null) {
            throw new NotSavedSubEntityException("Non existing room id");
        }
        if(message.getText() == null || message.getText().isEmpty()){
            throw new NotSavedSubEntityException("Non existing text");
        }
        if(message.getDateTime() == null){
            throw new NotSavedSubEntityException("Non existing date");
        }
    }
}
