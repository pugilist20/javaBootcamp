package school21.spring.service.application;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import school21.spring.service.config.ApplicationConfig;
import school21.spring.service.repositories.UsersRepositoryJdbcTemplateImpl;
import school21.spring.service.services.UsersService;
import school21.spring.service.services.UsersServiceImpl;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        UsersRepositoryJdbcTemplateImpl usersRepository = context.getBean("usersRepositoryJdbcTemplateImpl",UsersRepositoryJdbcTemplateImpl.class);
        usersRepository.delete(usersRepository.findByEmail("user1@gmail.com").get().getId());
//        UsersService userService = context.getBean("usersServiceImpl", UsersServiceImpl.class);
//        System.out.println(userService.signUp("user1@gmail.com"));
    }
}
