package services.interfaces;

import domain.Product;
import domain.User;

public interface IProductService {
    User getProductByID(long product_id);
    User getProductByName(String product_name);

    void addProduct(Product product);

    void updateProduct(Product product);
}
