package edu.school21.repositories;

import edu.school21.models.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class ProductsRepositoryJdbcImplTest {
    private static final List<Product> EXPECTED_FIND_ALL_PRODUCTS = Arrays.asList(new Product(0L, "apple", 125L),
            new Product(1L, "milk", 40L),
            new Product(2L, "eggs", 70L),
            new Product(3L, "orange", 80L),
            new Product(4L, "juice", 90L),
            new Product(5L, "butter", 55L),
            new Product(6L, "grape", 85L));
    final Product EXPECTED_FIND_BY_ID_PRODUCT = new Product(2L, "eggs", 70L) ;
    final Product EXPECTED_UPDATED_PRODUCT = new Product(2L, "orange", 90L) ;
    private EmbeddedDatabase embeddedDatabase;
    private ProductsRepositoryJdbcImpl productsRepository;

    @BeforeEach
    void init() throws SQLException {
        embeddedDatabase = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.HSQL)
                .addScript("schema.sql")
                .addScript("data.sql")
                .build();
        productsRepository=new ProductsRepositoryJdbcImpl(embeddedDatabase);
    }

    @Test
    void testFindAll() throws SQLException {
        Assertions.assertEquals(productsRepository.findAll(), EXPECTED_FIND_ALL_PRODUCTS);
    }

    @Test
    void testFindById() throws SQLException {
        Assertions.assertEquals(productsRepository.findById(2L).get(), EXPECTED_FIND_BY_ID_PRODUCT);
    }

    @Test
    void testUpdate() throws SQLException {
        productsRepository.update(new Product(2L, "orange", 90L));
        Assertions.assertEquals(productsRepository.findById(2L).get(), EXPECTED_UPDATED_PRODUCT);
    }

    @AfterEach
    void tearDown() throws SQLException {
        embeddedDatabase.shutdown();
    }
}
