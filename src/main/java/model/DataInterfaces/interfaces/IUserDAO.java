package model.DataInterfaces.interfaces;

import model.classes.User;

public interface IUserDAO extends IDefaultDAO<User, Long> {
    User findUserByLoginData(String login, String password);
    User findUserByLogin(String login);
}
