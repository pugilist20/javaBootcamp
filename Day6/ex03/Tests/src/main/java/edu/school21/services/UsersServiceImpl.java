package edu.school21.services;

import edu.school21.exceptions.AlreadyAuthenticatedException;
import edu.school21.exceptions.EntityNotFoundException;
import edu.school21.models.User;
import edu.school21.repositories.UsersRepository;

import javax.sql.DataSource;
import java.sql.*;

public class UsersServiceImpl implements UsersRepository {
    Connection connection;

    public UsersServiceImpl(DataSource dataSource) throws SQLException {
        this.connection = dataSource.getConnection();
    }

    @Override
    public User findByLogin(String login) throws SQLException, EntityNotFoundException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from PUBLIC.USERS where LOGIN=?");
        preparedStatement.setString(1, login);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Integer productID = resultSet.getInt("id");
            String username = resultSet.getString("login");
            String password = resultSet.getString("login");
            boolean authentication = resultSet.getBoolean("authentication");
            return new User(productID, username, password, authentication);
        }
        throw new EntityNotFoundException("User not found");
    }

    @Override
    public void update(User user) throws SQLException, EntityNotFoundException {
        PreparedStatement preparedStatement = connection.prepareStatement("update PUBLIC.USERS set LOGIN=?,PASSWORD=?,AUTHENTICATION=? where  id=?  ");
        if (user.getLogin() != null) {
            preparedStatement.setString(1, user.getLogin());
        } else {
            preparedStatement.setNull(1, Types.VARCHAR);
        }
        if (user.getPassword() != null) {
            preparedStatement.setString(1, user.getPassword());
        } else {
            preparedStatement.setNull(1, Types.VARCHAR);
        }
        preparedStatement.setLong(3, user.getId());
        int count = preparedStatement.executeUpdate();
        if (count == 0) {
            throw new EntityNotFoundException("User not found");
        }
    }

    @Override
    public boolean authenticate(String login, String password) throws SQLException, EntityNotFoundException, AlreadyAuthenticatedException {
        if (login != null && password != null) {
            User user = findByLogin(login);
            if (user.isStatus()) {
                throw new AlreadyAuthenticatedException("User is already authenticated");
            }
            if (user.getPassword().equals(password)) {
                user.setStatus(true);
                update(user);
                return true;
            }
        }
        return false;
    }
}
