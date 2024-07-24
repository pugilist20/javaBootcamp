package edu.school21.services;

import edu.school21.exceptions.AlreadyAuthenticatedException;
import edu.school21.exceptions.EntityNotFoundException;
import edu.school21.repositories.UsersRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.sql.SQLException;

public class UsersServiceImplTest {
    @Mock
    private UsersRepository usersRepository=Mockito.mock(UsersRepository.class);
    @Test
    public void testAuthenticateCorrect() throws SQLException, EntityNotFoundException, AlreadyAuthenticatedException {
        Mockito.when(usersRepository.authenticate("user1", "pass")).thenReturn(true);
        Assertions.assertTrue(usersRepository.authenticate("user1", "pass"));
    }
    @Test
    public void testAuthenticateIncorrect() throws SQLException, EntityNotFoundException, AlreadyAuthenticatedException {
        Mockito.when(usersRepository.authenticate("user1", "pass")).thenReturn(false);
        Assertions.assertFalse(usersRepository.authenticate("user1", "pass"));
    }
    @Test
    public void testAuthenticateIncorrectLogin() throws SQLException, EntityNotFoundException, AlreadyAuthenticatedException {
        Mockito.when(usersRepository.findByLogin("user1")).thenThrow(EntityNotFoundException.class);
        Assertions.assertThrows(EntityNotFoundException.class, ()-> usersRepository.findByLogin("user1"));
    }
    @Test
    public void testAuthenticateIncorrectPassword() throws SQLException, EntityNotFoundException, AlreadyAuthenticatedException {
        Mockito.when(usersRepository.authenticate("user1", "pass")).thenReturn(false);
        Assertions.assertFalse(usersRepository.authenticate("user1", "pass"));
    }
    
}
