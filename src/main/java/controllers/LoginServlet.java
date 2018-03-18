package controllers;

import model.DataInterfaces.UserDAO;
import model.DataInterfaces.interfaces.IUserDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.Logger;

public class LoginServlet extends HttpServlet {

    private static IUserDAO userDAO = new UserDAO();
    private static final Logger log = Logger.getLogger(LoginServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if(userDAO.findUserByLoginData(login, password) != null) {
            req.getSession().setAttribute("user", login);
//            req.getRequestDispatcher("/productList.jsp").forward(req, resp);
            log.info("User " + login + " logged in succesfuly");
            resp.sendRedirect(req.getContextPath()+"/");
        } else  {
            log.info("User " + login + " failed to login");
            resp.sendRedirect(req.getContextPath() + "/error");
        }

        //super.doPost(req, resp);
    }
}
