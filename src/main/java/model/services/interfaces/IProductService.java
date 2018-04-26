package model.services.interfaces;

import model.classes.Product;

import java.util.List;

public interface IProductService {
    public Product findByDescription(String description);
    public List<Product> getAll();
    public Long insert(String description, int amount);
    public Long update(Product product);
    public Long delete(Product product);
}
