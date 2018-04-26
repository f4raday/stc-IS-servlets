package model.services.interfaces;

import model.DataInterfaces.UserDAO;
import model.DataInterfaces.interfaces.IUserDAO;
import model.classes.User;

public interface IUserService {
    public User findUserByLoginData(String login, String password);
    public User findUserByLogin(String login);
    public User getById(Long id);
    public Long save(String login, String password, String name);
    public Long remove(User entry);

}
