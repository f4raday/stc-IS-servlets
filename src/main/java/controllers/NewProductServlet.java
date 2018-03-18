package controllers;

import model.DataInterfaces.ProductDAO;
import model.DataInterfaces.interfaces.IProductDAO;
import model.classes.Product;
import model.classes.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.Logger;

public class NewProductServlet extends HttpServlet{

    private static IProductDAO productDAO = new ProductDAO();

    private static final Logger log = Logger.getLogger(NewProductServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession(false);
        if(httpSession == null || httpSession.getAttribute("user") == null) {
            log.info("User sent to login");
            resp.sendRedirect(req.getContextPath() + "/login");
        } else {
            log.info("User sent to add new product");
            req.getRequestDispatcher("/newProduct.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String description = req.getParameter("description");
        int availableAmount = 0;
        try {
            availableAmount = Integer.parseInt(req.getParameter("availableAmount"));
            log.info("new product added");
        } catch (NumberFormatException e) {
            log.info(e.getMessage());
        }
        if (availableAmount<0)
            availableAmount = 0;

        productDAO.insert(new Product(description, availableAmount));

        resp.sendRedirect(req.getContextPath() + "/");
    }
}
