package model.DataInterfaces;

import model.DataInterfaces.interfaces.IProductDAO;
import model.classes.Product;
import model.services.DataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ProductDAO implements IProductDAO{

    private static final Logger log = Logger.getLogger(ProductDAO.class.getName());

    @Override
    public Product getByID(Long id) {
        Product product = null;

        String DEFAULT_SQL_QUERY = "SELECT * FROM products WHERE id = ?;";

        try (Connection connection = DataBase.getConnection();){
            PreparedStatement preparedStatement = connection.prepareStatement(DEFAULT_SQL_QUERY);
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next())
                product = new Product(resultSet.getLong("id"), resultSet.getString("description"),
                    resultSet.getInt("availableAmount"));
            else
                log.info("product with id " + id + " not found");



            resultSet.close();
            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
            log.info(e.getMessage());
        }


        return product;
    }

    @Override
    public List<Product> getAll() {
        ArrayList<Product> products = new ArrayList<>();

        String DEFAULT_SQL_QUERY = "SELECT * FROM products;";

        try (Connection connection = DataBase.getConnection()){

            PreparedStatement preparedStatement = connection.prepareStatement(DEFAULT_SQL_QUERY);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {

                Product product = new Product(resultSet.getString("description"),
                        resultSet.getInt("availableAmount"));
                products.add(product);
            }


            resultSet.close();
            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
            log.info(e.getMessage());
        }


        return products;
    }

    @Override
    public Long insert(Product entry) {
        long id = 0;

        String DEFAULT_SQL_QUERY = "INSERT INTO products (description, availableAmount) VALUES (?, ?)";

        try (Connection connection = DataBase.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(DEFAULT_SQL_QUERY);
            preparedStatement.setString(1, entry.getDescription());
            preparedStatement.setInt(2, entry.getAvailableAmount());
            preparedStatement.executeUpdate();


            log.info("new product added");

            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
            log.info(e.getMessage());
        }

        return id;
    }

    @Override
    public Long update(Product entry) {
        long id = 0;

        String DEFAULT_SQL_QUERY = "UPDATE products SET availableamount = ? WHERE description = ?;";

        try (Connection connection = DataBase.getConnection()){

            PreparedStatement preparedStatement = connection.prepareStatement(DEFAULT_SQL_QUERY);
            preparedStatement.setInt(1, entry.getAvailableAmount());
            preparedStatement.setString(2, entry.getDescription());

            ResultSet resultSet = preparedStatement.executeQuery();

            log.info("Product updated");

            resultSet.close();
            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
            log.info(e.getMessage());
        }


        return id;
    }

    @Override
    public Long delete(Product entry) {
        return null;
    }

    @Override
    public Product getByDescription(String description) {
        Product product = null;

        String DEFAULT_SQL_QUERY = "SELECT * FROM products WHERE description = ?;";

        try (Connection connection = DataBase.getConnection()){

            PreparedStatement preparedStatement = connection.prepareStatement(DEFAULT_SQL_QUERY);
            preparedStatement.setString(1, description);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next())
                product = new Product(resultSet.getString("description"),
                    resultSet.getInt("availableAmount"));
            else
                log.info("Product with description " + description + " not found");



            resultSet.close();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
            log.info(e.getMessage());
        }


        return product;
    }
}
