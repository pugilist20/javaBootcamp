package school21.spring.service.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import school21.spring.service.config.TestApplicationConfig;
import school21.spring.service.repositories.UsersRepositoryJdbcImpl;
import school21.spring.service.repositories.UsersRepositoryJdbcTemplateImpl;

import java.sql.SQLException;

@Component
public class UsersServiceImplTest {
    private static UsersService usersService;
    private static UsersService usersServiceTemplate;
    private static UsersRepositoryJdbcImpl usersRepositoryJdbc;
    private static UsersRepositoryJdbcTemplateImpl usersRepositoryJdbcTemplate;

    @BeforeAll
    public static void setUp() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(TestApplicationConfig.class);
        usersService = applicationContext.getBean("usersServiceJdbcImpl", UsersService.class);
        usersRepositoryJdbc = applicationContext.getBean("usersRepositoryJdbcImpl", UsersRepositoryJdbcImpl.class);
        usersServiceTemplate = applicationContext.getBean("usersServiceJdbcTemplateImpl", UsersService.class);
        usersRepositoryJdbcTemplate = applicationContext.getBean("usersRepositoryJdbcTemplateImpl", UsersRepositoryJdbcTemplateImpl.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"email1"})
    public void testSignUp(String email) throws SQLException {
        usersService.signUp(email);
        Assertions.assertEquals(email, usersRepositoryJdbc.findById(0L).get().getEmail());
    }

    @ParameterizedTest
    @ValueSource(strings = {"email2"})
    public void testSignUpTemplate(String email) throws SQLException {
        usersServiceTemplate.signUp(email);
        Assertions.assertEquals(email, usersRepositoryJdbcTemplate.findById(1L).get().getEmail());
    }
}
