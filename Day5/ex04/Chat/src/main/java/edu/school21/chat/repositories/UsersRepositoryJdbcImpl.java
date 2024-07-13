package edu.school21.chat.repositories;

import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UsersRepositoryJdbcImpl implements UsersRepository {
    Connection connection;

    public UsersRepositoryJdbcImpl(DataSource dataSource) throws SQLException {
        this.connection = dataSource.getConnection();
    }

    @Override
    public List<User> findAll(int page, int size) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("with paginated_users as (select users.id as user_id, login, password, c.id as chatroom_id, name\n" +
                "                         from chat.users\n" +
                "                                  left join chat.chatrooms c on users.id = c.ownerid\n" +
                "                         order by users.id, c.id\n" +
                "                         limit ? offset ?)\n" +
                "select paginated_users.user_id,\n" +
                "       login,\n" +
                "       password,\n" +
                "       array_agg(distinct chatroomid)    as created_rooms,\n" +
                "       array_agg(distinct uc.chatroomid) as rooms\n" +
                "from paginated_users\n" +
                "         left join chat.user_chatrooms uc on uc.userid = paginated_users.user_id\n" +
                "group by 1, 2, 3\n" +
                "order by 1;");
        preparedStatement.setInt(1, size);
        preparedStatement.setInt(2, page * size);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<User> allUsers = new ArrayList<>();
        while (resultSet.next()) {
            int user_id = resultSet.getInt("user_id");
            String login = resultSet.getString("login");
            String password = resultSet.getString("password");
            ArrayList<Chatroom> createdRooms = getAllChatrooms("created_rooms", resultSet);
            ArrayList<Chatroom> rooms = getAllChatrooms("rooms", resultSet);
            allUsers.add(new User(user_id, login, password, createdRooms, rooms));
        }
        return allUsers;
    }

    public ArrayList<Chatroom> getAllChatrooms(String columnLabel, ResultSet resultSet) throws SQLException {
        ArrayList<Chatroom> chatrooms = new ArrayList<>();
        String listOfChatrooms = resultSet.getString(columnLabel);
        if (listOfChatrooms != null && !listOfChatrooms.isEmpty()) {
            listOfChatrooms = listOfChatrooms.replace("{", "").replace("}", "");
            String[] stringOfChatrooms = listOfChatrooms.split(",");
            for (String stringOfChatroom : stringOfChatrooms) {
                if(stringOfChatroom!=null&&!stringOfChatroom.equals("NULL")) {
                    chatrooms.add(findChatroomById(Integer.parseInt(stringOfChatroom)));
                }
            }
        }

        return chatrooms;
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
}
