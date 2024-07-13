package edu.school21.chat.repositories;

import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class MessagesRepositoryJdbcImpl implements MessagesRepository {
    Connection connection;
    public MessagesRepositoryJdbcImpl(DataSource dataSource) throws SQLException {
        connection=dataSource.getConnection();
    }
    @Override
    public Optional<Message> findById(Long id) throws SQLException {
        PreparedStatement preparedStatement=connection.prepareStatement("SELECT * from chat.messages where id=?");
        preparedStatement.setLong(1, id);
        ResultSet resultSet=preparedStatement.executeQuery();
        while (resultSet.next()) {
            int messageID = resultSet.getInt("id");
            int authorID = resultSet.getInt("authorid");
            int chatroomID = resultSet.getInt("chatroomid");
            String text = resultSet.getString("text");
            String datetime = resultSet.getString("datetime").replace('-','/').substring(2,16);
            System.out.println("Message : {\n id="+messageID + ",\n " + findUserById((long)authorID) + ",\n " + findChatroomById((long) chatroomID) + ",\n text=\"" +text + "\",\n datetime=" + datetime+"\n}");
        }
        return Optional.empty();
    }
    public User findUserById(Long id) throws SQLException {
        PreparedStatement preparedStatement=connection.prepareStatement("SELECT * from chat.users where id=?");
        preparedStatement.setLong(1, id);
        ResultSet resultSet=preparedStatement.executeQuery();
        while (resultSet.next()) {
            int userID = resultSet.getInt("id");
            String login = resultSet.getString("login");
            String password = resultSet.getString("password");
            return new User(userID,login,password, new ArrayList<>(), new ArrayList<>());
        }
        return null;
    }
    public Chatroom findChatroomById(Long id) throws SQLException {
        PreparedStatement preparedStatement=connection.prepareStatement("SELECT * from chat.chatrooms where id=?");
        preparedStatement.setLong(1, id);
        ResultSet resultSet=preparedStatement.executeQuery();
        while (resultSet.next()) {
            int chatroomID = resultSet.getInt("id");
            String name = resultSet.getString("name");
            int ownerID = resultSet.getInt("ownerID");
            return new Chatroom(chatroomID,name, ownerID, new ArrayList<>()) ;
        }
        return null;
    }
}
