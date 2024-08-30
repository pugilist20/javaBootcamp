package school21.spring.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import school21.spring.service.repositories.UsersRepository;
import school21.spring.service.repositories.UsersRepositoryJdbcImpl;
import school21.spring.service.repositories.UsersRepositoryJdbcTemplateImpl;
import school21.spring.service.services.UsersService;
import school21.spring.service.services.UsersServiceImpl;

import java.sql.SQLException;

@Configuration
public class TestApplicationConfig {
    @Bean
    EmbeddedDatabase embeddedDatabase() {
        return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.HSQL).addScript("schema.sql").build();
    }

    @Bean
    UsersRepository usersRepositoryJdbcImpl() throws SQLException {
        return new UsersRepositoryJdbcImpl(embeddedDatabase());
    }

    @Bean
    UsersRepository usersRepositoryJdbcTemplateImpl() throws SQLException {
        return new UsersRepositoryJdbcTemplateImpl(embeddedDatabase());
    }

    @Bean
    UsersService usersServiceJdbcImpl() throws SQLException {
        return new UsersServiceImpl(usersRepositoryJdbcImpl());
    }

    @Bean
    UsersService usersServiceJdbcTemplateImpl() throws SQLException {
        return new UsersServiceImpl(usersRepositoryJdbcTemplateImpl());
    }
}
