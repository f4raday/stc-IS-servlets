package controllers;

import model.DataInterfaces.UserDAO;
import model.DataInterfaces.interfaces.IUserDAO;
import model.classes.User;
import model.services.UserService;
import model.services.interfaces.IUserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

public class RegistrationServlet extends HttpServlet{

//    private static IUserDAO userDAO = new UserDAO();
    private static IUserService userService = new UserService();

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

        if(login == "" || password == "" || name == "") {
            log.info("Registration failed. User passed empty parameter");

            req.setAttribute("errorMsg", "All fields must be non-empty");
            req.getRequestDispatcher("/register.jsp").forward(req, resp);

            return;
        }

        User user = userService.findUserByLogin(login);
        if (user == null) {

            log.info("Registration failed. No connection to database");

            req.setAttribute("errorMsg", "Problem with database. Contact support");
            req.getRequestDispatcher("/register.jsp").forward(req, resp);
        } else if( !user.equals(User.Empty())) {
            log.info("Registration failed. User already exists");

            req.setAttribute("errorMsg", "All fields must be non-empty");
            req.getRequestDispatcher("/register.jsp").forward(req, resp);

            return;
        } else {
            userService.save(login, password, name);
            log.info("Registration succesful");
        }

        resp.sendRedirect(req.getContextPath() + "/");
    }
}
