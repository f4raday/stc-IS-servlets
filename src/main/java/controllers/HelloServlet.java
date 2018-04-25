package controllers;

import model.DataInterfaces.ProductDAO;
import model.DataInterfaces.interfaces.IProductDAO;
import model.classes.Product;
import model.services.ProductService;
import model.services.interfaces.IProductService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

public class HelloServlet extends HttpServlet {

//    private static IProductDAO productDAO = new ProductDAO();
    private static IProductService productService = new ProductService();

    private static final Logger log = Logger.getLogger(HelloServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        HttpSession httpSession = req.getSession(false); // Moved to filters
//        if(httpSession == null || httpSession.getAttribute("user") == null) {
//            log.info("User sent to login");
//            resp.sendRedirect(req.getContextPath() + "/login");
//        } else {
            log.info("Show products to user");
            ShowProducts(req, resp);
//        }


//        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //resp.sendRedirect(req.getContextPath() + "/login");
        //super.doPost(req, resp);

        String element = req.getParameter("elem");

        Product product = productService.findByDescription(element);

        if(product != null && product.getAvailableAmount()>0) {
            product.addAmount(-1);
            productService.update(product);
            req.setAttribute("purchase_result", "Purchase completed");
            log.info("Purchased completed");
        } else {
            log.info("Purchase failed");
            req.setAttribute("purchase_result", "Can't make purchase");
        }

        req.getRequestDispatcher("/onPurchase.jsp").forward(req,resp);
    }


    private void ShowProducts(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> products = productService.getAll();

        if(products == null)
            req.setAttribute("errorMsg", "Problem connecting to the server database. Contact support");

        req.setAttribute("products", products);
        req.getRequestDispatcher("/productList.jsp").forward(req, resp);
    }
}
