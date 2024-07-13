package edu.school21.chat.app;

import edu.school21.chat.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CRUDUtils {
    public static List<User> getUserData(String query) {
        List<User> users = new ArrayList<>();
        try (Connection connection = SQLLogic.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id=rs.getInt("id");
                String login = rs.getString("login");
                String password = rs.getString("password");
                users.add(new User(id, login, password));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }
}
