package model.services;

import model.DataInterfaces.ProductDAO;
import model.DataInterfaces.interfaces.IProductDAO;
import model.classes.Product;
import model.services.interfaces.IProductService;

import java.util.List;

public class ProductService implements IProductService {

    private static IProductDAO productDAO = new ProductDAO();

    @Override
    public Product findByDescription(String description) {
        return productDAO.getByDescription(description);
    }

    @Override
    public List<Product> getAll() {
        return productDAO.getAll();
    }

    @Override
    public Long insert(String description, int amount) {
        Product product = new Product(description, amount);
        return productDAO.insert(product);
    }

    @Override
    public Long update(Product product) {
        return productDAO.update(product);
    }
}
