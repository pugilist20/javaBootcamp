package school21.spring.service.repositories;

import school21.spring.service.models.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcImpl implements UsersRepository {
    Connection connection;

    public UsersRepositoryJdbcImpl(DataSource dataSource) throws SQLException {
        this.connection = dataSource.getConnection();
    }

    @Override
    public List<User> findAll() throws SQLException {
        List<User> users = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM chat.users");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            users.add(new User(resultSet.getLong("id"), resultSet.getString("email")));
        }
        return users;
    }

    @Override
    public void save(User user) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO chat.users values (?, ?)");
        preparedStatement.setLong(1, user.getId());
        preparedStatement.setString(2, user.getEmail());
        preparedStatement.executeUpdate();
    }

    @Override
    public void update(User user) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE chat.users set email=? where id=?");
        preparedStatement.setString(1, user.getEmail());
        preparedStatement.setLong(2, user.getId());
        preparedStatement.executeUpdate();
    }

    @Override
    public void delete(Long id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM chat.users WHERE ID=?");
        preparedStatement.setLong(1, id);
        preparedStatement.executeUpdate();
    }

    @Override
    public Optional<User> findById(Long id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM chat.users WHERE ID=?");
        preparedStatement.setLong(1, id);
        return getUser(preparedStatement);
    }

    @Override
    public Optional<User> findByEmail(String email) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM chat.users WHERE email=?");
        preparedStatement.setString(1, email);
        return getUser(preparedStatement);
    }

    private Optional<User> getUser(PreparedStatement preparedStatement) throws SQLException {
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            User user = new User(resultSet.getLong("id"), resultSet.getString("email"));
            System.out.println(user);
            return Optional.of(user);
        }
        return Optional.empty();
    }
}