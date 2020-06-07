package services;

import domain.Product;
import domain.User;
import repositories.ProductRepository;
import repositories.UserRepository;
import repositories.interfaces.IProductRepository;
import repositories.interfaces.IUserRepository;
import services.interfaces.IProductService;

public class ProductService implements IProductService {
    private IProductRepository productRepo = new ProductRepository();
    @Override
    public User getProductByID(long product_id) {
        return null;
    }

    @Override
    public User getProductByName(String product_name) {
        return null;
    }

    @Override
    public void addProduct(Product product) {

    }

    @Override
    public void updateProduct(Product product) {

    }
}
