package school21.spring.service.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import school21.spring.service.models.User;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Component
public class UsersRepositoryJdbcTemplateImpl implements UsersRepository {

    private final JdbcTemplate jdbcTemplate;
@Autowired
    public UsersRepositoryJdbcTemplateImpl(@Qualifier("hikariDataSource") DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Optional<User> findByEmail(String email){
        List<User> users = jdbcTemplate.query(
                "select * from chat.users where email = ?",
                new Object[]{email},
                new BeanPropertyRowMapper<>(User.class));
        return users.isEmpty() ? Optional.empty() : Optional.ofNullable(users.get(0));
    }

    @Override
    public Optional<User> findById(Long id){
        List<User> users = jdbcTemplate.query(
                "select * from chat.users where id = ?",
                new Object[]{id},
                new BeanPropertyRowMapper<>(User.class));
        return users.isEmpty() ? Optional.empty() : Optional.ofNullable(users.get(0));
    }

    @Override
    public List<User> findAll() {
        List<User> users;
        users=jdbcTemplate.queryForList("select * from chat.users", User.class);
        return users;
    }

    @Override
    public void save(User user){
        jdbcTemplate.update("INSERT INTO chat.users(email, password) values (?, ?)", user.getEmail(), user.getPassword());
    }

    @Override
    public void update(User user){
        jdbcTemplate.update("UPDATE chat.users set email=?, password=? where id=?", user.getEmail(), user.getPassword(),  user.getId());
    }

    @Override
    public void delete(Long id){
        jdbcTemplate.update("DELETE FROM chat.users where id=?", id);
    }
}
