package school21.spring.service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import school21.spring.service.models.User;
import school21.spring.service.repositories.UsersRepository;

import java.sql.SQLException;
import java.util.UUID;

@Component("usersServiceImpl")
public class UsersServiceImpl implements UsersService {
    UsersRepository usersRepository;
    private Long id = 0L;

    @Autowired
    public UsersServiceImpl(@Qualifier("usersRepositoryJdbcTemplateImpl") UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    private String getRandomPassword() {
        return UUID.randomUUID().toString();
    }

    @Override
    public String signUp(String email) throws SQLException {
        if (usersRepository.findByEmail(email).isEmpty()) {
            String password = getRandomPassword();
            usersRepository.save(new User(id++, email, password));
            return password;
        } else {
            System.out.println("User already exists");
        }
        return null;
    }
}
