package controllers;

import model.DataInterfaces.ProductDAO;
import model.DataInterfaces.interfaces.IProductDAO;
import model.classes.Product;
import model.classes.User;
import model.services.ProductService;
import model.services.interfaces.IProductService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.Logger;

public class NewProductServlet extends HttpServlet{

//    private static IProductDAO productDAO = new ProductDAO();
    private static IProductService productService = new ProductService();

    private static final Logger log = Logger.getLogger(NewProductServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        HttpSession httpSession = req.getSession(false); // Moved to filter
//        if(httpSession == null || httpSession.getAttribute("user") == null) {
//            log.info("User sent to login");
//            resp.sendRedirect(req.getContextPath() + "/login");
//        } else {
            log.info("User sent to add new product");
            req.getRequestDispatcher("/newProduct.jsp").forward(req, resp);
//        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String description = req.getParameter("description");
        int availableAmount = 0;

        if (description == "") {
            req.setAttribute("errorMsg", "Description must be non-empty");
            req.getRequestDispatcher("/newProduct.jsp").forward(req, resp);
        } else {
            try {
                availableAmount = Integer.parseInt(req.getParameter("availableAmount"));
                log.info("new product added");

                if(availableAmount<=0) {
                    req.setAttribute("errorMsg", "Product amount must be > 0");
                    req.getRequestDispatcher("/newProduct.jsp").forward(req, resp);
                }
                else {
                    productService.insert(description, availableAmount);
                    resp.sendRedirect(req.getContextPath() + "/");

                    return;
                }

            } catch (NumberFormatException e) {
                log.info(e.getMessage());

                req.setAttribute("errorMsg", "Amount must be a number > 0");
                req.getRequestDispatcher("/newProduct.jsp").forward(req, resp);
            }
        }


    }
}
