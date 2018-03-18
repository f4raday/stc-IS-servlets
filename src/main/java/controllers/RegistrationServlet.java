package controllers;

import model.DataInterfaces.UserDAO;
import model.DataInterfaces.interfaces.IUserDAO;
import model.classes.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

public class RegistrationServlet extends HttpServlet{

    private static IUserDAO userDAO = new UserDAO();

    private static final Logger log = Logger.getLogger(RegistrationServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("Registration started");
        req.getRequestDispatcher("/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String name = req.getParameter("name");

        if(userDAO.findUserByLogin(login) == null) {
            userDAO.insert(new User(login, password, name));
            log.info("Registration succesful");
        } else {
            log.info("Registration failed. User already exists");
        }

        resp.sendRedirect(req.getContextPath() + "/");
    }
}
