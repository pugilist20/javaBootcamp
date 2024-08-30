package school21.spring.service.application;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import school21.spring.service.models.User;
import school21.spring.service.repositories.UsersRepository;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        UsersRepository usersRepositoryJdbc = context.getBean("usersRepositoryJdbcImpl", UsersRepository.class);
        System.out.println("Find user with id = 1: " + usersRepositoryJdbc.findById(1L).get());
        System.out.println("Find all users: " + usersRepositoryJdbc.findAll());

        usersRepositoryJdbc.delete(2L);
        if(usersRepositoryJdbc.findById(2L).isEmpty()){
            System.out.println("User with id 2 has been deleted successfully");
        }

        usersRepositoryJdbc.save(new User(7L, "email7"));
        System.out.println("Has been saved user with id = 7: "+ usersRepositoryJdbc.findById(7L).get());

        usersRepositoryJdbc.update(new User(1L, "email11"));
        System.out.println("After update user with id = 1: "+ usersRepositoryJdbc.findById(1L).get());

        System.out.println("Find by email user: " +usersRepositoryJdbc.findByEmail("email7").get());
        System.out.println();

        System.out.println("JDBC Template");
        UsersRepository usersRepositoryJdbcTemplate = context.getBean("usersRepositoryJdbcTemplateImpl", UsersRepository.class);
        System.out.println("Find user with id = 1: " + usersRepositoryJdbcTemplate.findById(1L).get());
        System.out.println("Find all users: " + usersRepositoryJdbcTemplate.findAll());

        usersRepositoryJdbcTemplate.delete(3L);
        if(usersRepositoryJdbcTemplate.findById(3L).isEmpty()){
            System.out.println("User with id 3 has been deleted successfully");
        }

        usersRepositoryJdbcTemplate.save(new User(8L, "email8"));
        System.out.println("Has been saved user with id = 8: "+ usersRepositoryJdbcTemplate.findById(8L).get());

        usersRepositoryJdbcTemplate.update(new User(5L, "email55"));
        System.out.println("After update user with id = 1: "+ usersRepositoryJdbcTemplate.findById(1L).get());
        System.out.println("Find by email user with id = 4: " +usersRepositoryJdbcTemplate.findByEmail("email55").get());
        System.out.println();
    }
}
