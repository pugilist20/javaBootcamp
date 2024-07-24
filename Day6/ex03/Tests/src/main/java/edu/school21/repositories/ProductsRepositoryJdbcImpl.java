package edu.school21.repositories;

import edu.school21.models.Product;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductsRepositoryJdbcImpl implements ProductsRepository {
    private Connection connection;

    public ProductsRepositoryJdbcImpl(DataSource dataSource) throws SQLException {
        connection= dataSource.getConnection();
    }

    @Override
    public List<Product> findAll() throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("select * from SHOP.PRODUCTS");
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Product> allUsers = new ArrayList<>();
        while (resultSet.next()) {
            Long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            Long price  = resultSet.getLong("price");
            allUsers.add(new Product(id, name, price));
        }
        return allUsers;
    }

    @Override
    public Optional<Product> findById(Long id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from SHOP.PRODUCTS where id=?");
        preparedStatement.setLong(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Long productID = resultSet.getLong("id");
            String name = resultSet.getString("name");
            Long price = resultSet.getLong("price");
            Product product=new Product(productID,name, price);
            return Optional.of(product);
        }
        return Optional.empty();
    }

    @Override
    public void update(Product product) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("update SHOP.products set NAME=?, PRICE=? where  id=?  ");
        if(product.getName() != null) {
            preparedStatement.setString(1, product.getName());
        }
        else{
            preparedStatement.setNull(1, Types.VARCHAR);
        }
        if(product.getPrice()>0 || product.getPrice() != null) {
            preparedStatement.setLong(2, product.getPrice());
        }
        else{
            preparedStatement.setNull(2, Types.INTEGER);
        }
        preparedStatement.setLong(3, product.getId());
        preparedStatement.executeUpdate();
    }

    @Override
    public void save(Product product) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO SHOP.PRODUCTS (id, name, PRICE) VALUES (?, ?, ?)");
        preparedStatement.setLong(1, product.getId());
        preparedStatement.setString(2, product.getName());
        preparedStatement.setLong(3, product.getPrice());
        preparedStatement.execute();
    }

    @Override
    public void delete(Long id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("delete from SHOP.PRODUCTS where ID=?");
        preparedStatement.setLong(1, id);
        preparedStatement.executeUpdate();
    }
}
