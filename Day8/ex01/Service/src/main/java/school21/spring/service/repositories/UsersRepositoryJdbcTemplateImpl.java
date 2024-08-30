package school21.spring.service.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import school21.spring.service.models.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcTemplateImpl implements UsersRepository {

    private JdbcTemplate jdbcTemplate;

    public UsersRepositoryJdbcTemplateImpl(DriverManagerDataSource dataSource) throws SQLException {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Optional<User> findByEmail(String email) throws SQLException {
        User user = jdbcTemplate.queryForObject("SELECT * from chat.users where email=?", User.class, email);
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> findById(Long id) throws SQLException {
        User user = jdbcTemplate.queryForObject("SELECT * from chat.users where id=?", User.class, id);
        return Optional.ofNullable(user);
    }

    @Override
    public List<User> findAll() throws SQLException {
        List<User> users=new ArrayList<>();
        users=jdbcTemplate.queryForList("select * from chat.users", User.class);
        return users;
    }

    @Override
    public void save(User user) throws SQLException {
        jdbcTemplate.update("INSERT INTO chat.users(email) values (?)", user.getEmail());
    }

    @Override
    public void update(User user) throws SQLException {
        jdbcTemplate.update("UPDATE chat.users set email=? where id=?", user.getEmail(), user.getId());
    }

    @Override
    public void delete(Long id) throws SQLException {
        jdbcTemplate.update("DELETE FROM chat.users where id=?", id);
    }
}
