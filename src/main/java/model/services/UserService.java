package model.services;

import model.DataInterfaces.UserDAO;
import model.DataInterfaces.interfaces.IUserDAO;
import model.classes.User;
import model.services.interfaces.IUserService;

public class UserService implements IUserService {

    private static IUserDAO userDAO = new UserDAO();

    @Override
    public User findUserByLoginData(String login, String password) {
        return userDAO.findUserByLoginData(login, password);
    }

    @Override
    public User findUserByLogin(String login) {
        return userDAO.findUserByLogin(login);
    }

    @Override
    public User getById(Long id) {
        return userDAO.getByID(id);
    }

    @Override
    public Long save(String login, String password, String name) {
        User user = new User(login, password, name);
        return userDAO.insert(user);
    }
}
