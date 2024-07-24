package edu.school21.repositories;

import edu.school21.exceptions.AlreadyAuthenticatedException;
import edu.school21.exceptions.EntityNotFoundException;
import edu.school21.models.User;

import java.sql.SQLException;

public interface UsersRepository {
    User findByLogin(String login) throws SQLException, EntityNotFoundException;
    public void update(User user) throws SQLException, EntityNotFoundException;
    public boolean authenticate(String login, String password) throws SQLException, EntityNotFoundException, AlreadyAuthenticatedException ;
}
