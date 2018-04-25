package controllers;

import model.DataInterfaces.UserDAO;
import model.DataInterfaces.interfaces.IUserDAO;
import model.classes.User;
import model.services.ProductService;
import model.services.UserService;
import model.services.interfaces.IProductService;
import model.services.interfaces.IUserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.Logger;

public class LoginServlet extends HttpServlet {

//    private static IUserDAO userDAO = new UserDAO();
    private static IUserService userService = new UserService();

    private static final Logger log = Logger.getLogger(LoginServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if(password == "" || login == "") {

            req.setAttribute("errorMsg", "Login and password must be non-empty");

            req.getRequestDispatcher("/login.jsp").forward(req, resp);
            return;
        }

        User user = userService.findUserByLoginData(login, password);

        if (user == null) {
            req.setAttribute("errorMsg", "Problem with database. Contact support");

            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        } else if(user.equals(User.Empty())) {
            log.info("User " + login + " failed to login");

            req.setAttribute("errorMsg", "User with such login and password not found");

            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        } else  {
            req.getSession().setAttribute("user", login);
//            req.getRequestDispatcher("/productList.jsp").forward(req, resp);
            log.info("User " + login + " logged in succesfuly");
            resp.sendRedirect(req.getContextPath()+"/");
        }

        //super.doPost(req, resp);
    }
}
