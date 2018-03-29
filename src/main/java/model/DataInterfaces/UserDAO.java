package model.DataInterfaces;

import model.DataInterfaces.interfaces.IUserDAO;
import model.classes.User;
import model.services.DataBase;

import java.sql.*;
import java.util.List;
import java.util.logging.Logger;

public class UserDAO implements IUserDAO {

    private static final Logger log = Logger.getLogger(UserDAO.class.getName());


    @Override
    public User findUserByLoginData(String login, String password) {
        User user = null;

        String DEFAULT_SQL_QUERY = "SELECT * FROM users WHERE login = ? AND password = ?;";

        try (Connection connection = DataBase.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(DEFAULT_SQL_QUERY);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next())
                user = new User(resultSet.getString("login"),
                    resultSet.getString("password"), resultSet.getString("name"));
            else
                log.info("User with this login and passowrd not found");



            resultSet.close();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
            log.info(e.getMessage());
        }


        return user;
    }

    @Override
    public User findUserByLogin(String login) {
        User user = null;

        String DEFAULT_SQL_QUERY = "SELECT * FROM users WHERE login = ?;";

        try (Connection connection = DataBase.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(DEFAULT_SQL_QUERY);
            preparedStatement.setString(1, login);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next())
                user = new User(resultSet.getString("login"),
                    resultSet.getString("password"), resultSet.getString("name"));
            else
                log.info("User with login " + login + " not found");



            resultSet.close();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
            log.info(e.getMessage());
        }


        return user;

    }

    @Override
    public User getByID(Long id) {
        User user = null;

        String DEFAULT_SQL_QUERY = "SELECT * FROM users WHERE id = ?;";

        try (Connection connection = DataBase.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(DEFAULT_SQL_QUERY);
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                user = new User(resultSet.getString("login"),
                        resultSet.getString("password"), resultSet.getString("name"));
            } else
                log.info("User with id " + id + " not found");



            resultSet.close();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
            log.info(e.getMessage());
        }


        return user;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public Long insert(User entry) {
        long id = 0;

        String DEFAULT_SQL_QUERY = "INSERT INTO users (login, password, name) VALUES (?, ?, ?)";

        try (Connection connection = DataBase.getConnection()){
//            Connection connection = DataBase.getDataSource().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DEFAULT_SQL_QUERY);
            preparedStatement.setString(1, entry.getLogin());
            preparedStatement.setString(2, entry.getPassword());
            preparedStatement.setString(3, entry.getName());
            preparedStatement.executeUpdate();


            log.info("New user with login " + entry.getLogin() + " added");

//            ResultSet resultSet = preparedStatement.getResultSet();
//
//            if(resultSet != null && resultSet.next())
//                id = resultSet.getLong(1);
//
//
//
//
//            resultSet.close();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
            log.info(e.getMessage());
        }

        return id;
    }

    @Override
    public Long update(User entry) {
        return null;
    }

    @Override
    public Long delete(User entry) {
        return null;
    }


}
