package repositories.interfaces;

import domain.Product;


public interface IProductRepository extends IEntityRepository<Product> {
    void add(Product entity);
    void remove(Product entity);
    Product getProductByID(long product_id);
    Product getProductByName(String product_name) ;
}
