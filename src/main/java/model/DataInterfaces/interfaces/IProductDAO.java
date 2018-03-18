package model.DataInterfaces.interfaces;

import model.classes.Product;

public interface IProductDAO extends IDefaultDAO<Product, Long> {
    Product getByDescription(String description);
}
