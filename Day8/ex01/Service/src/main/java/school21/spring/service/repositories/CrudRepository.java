package school21.spring.service.repositories;

import school21.spring.service.models.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface CrudRepository {
    Optional<User> findById(Long id) throws SQLException;
    List<User> findAll() throws SQLException;
    void save(User user) throws SQLException;
    void update(User user) throws SQLException;
    void delete(Long id) throws SQLException;
}
