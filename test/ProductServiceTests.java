import model.classes.Product;
import model.classes.User;
import model.services.ProductService;
import model.services.interfaces.IProductService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ProductServiceTests {

    private IProductService productService;
    private Product testProduct;

    @Before
    public void setUpService() {
        productService = new ProductService();
        testProduct = new Product("JUNIT_TEST_PRODUCT", 42);
    }

    @Test
    public void newProductTest() {
        Assert.assertTrue(productService.findByDescription("JUNIT_TEST_PRODUCT").equals(Product.Empty()));
        productService.insert("JUNIT_TEST_PRODUCT",42);
        Assert.assertTrue(productService.findByDescription("JUNIT_TEST_PRODUCT").equals(testProduct));
    }

    @Test
    public void updateProductTest() {
        Assert.assertTrue(productService.findByDescription("JUNIT_TEST_PRODUCT").equals(Product.Empty()));
        productService.insert("JUNIT_TEST_PRODUCT",32);
        Product tempProduct = productService.findByDescription("JUNIT_TEST_PRODUCT");
        tempProduct.addAmount(10);
        productService.update(tempProduct);
        Assert.assertTrue(productService.findByDescription("JUNIT_TEST_PRODUCT").equals(testProduct));
    }

    @After
    public void cleanUpProduct() {
        productService.delete(testProduct);
    }
}
