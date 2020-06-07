package repositories;

import domain.Product;
import domain.Product;
import domain.User;
import domain.UserLoginData;
import repositories.interfaces.IDBRepository;
import repositories.interfaces.IProductRepository;

import javax.ws.rs.BadRequestException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class ProductRepository implements IProductRepository {
    private IDBRepository dbrepo = new PostgresRepository();

    @Override
    public void add(Product entity) {
        try {
            String sql = "INSERT INTO users(product_name,product_type,price) " +
                    "VALUES(?, ?, ?)";
            PreparedStatement stmt = dbrepo.getConnection().prepareStatement(sql);
            stmt.setString(1, entity.getProduct_name());
            stmt.setString(2, entity.getProduct_type());
            stmt.setString(3, String.valueOf(entity.getPrice()));
            stmt.execute();
        } catch (SQLException ex) {
            throw new BadRequestException("Cannot run SQL statement: " + ex.getMessage());
        }
    }

    @Override
    public void update(Product entity) {
        String sql = "UPDATE products " +
                "SET ";
        int c = 0;
        if (entity.getProduct_name() != null) {
            sql += "product_name=?, "; c++;
        }
        if (entity.getProduct_type() != null) {
            sql += "product_type=?, "; c++;
        }
        if (entity.getPrice() != null) {
            sql += "price=?, "; c++;
        }


        sql = sql.substring(0, sql.length() - 2);

        sql += " WHERE product_name = ?";

        try {
            int i = 1;
            PreparedStatement stmt = dbrepo.getConnection().prepareStatement(sql);
            if (entity.getProduct_name() != null) {
                stmt.setString(i++, entity.getProduct_name());
            }
            if (entity.getProduct_type() != null) {
                stmt.setString(i++, entity.getProduct_type());
            }
            if (entity.getPrice() != null) {
                stmt.setString(i++, entity.getPrice());
            }
            stmt.execute();
        } catch (SQLException ex) {
            throw new BadRequestException("Cannot run SQL statement: " + ex.getMessage());
        }
    }

    @Override
    public void delite(Product entity) {

    }

    @Override
    public List<Product> query(String sql) {
        try {
            Statement stmt = dbrepo.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            LinkedList<Product> products = new LinkedList<>();
            while (rs.next()) {
                Product product = new Product(
                        rs.getLong("product_id"),
                        rs.getString("product_name"),
                        rs.getString("product_type"),
                        rs.getInt("price")
                );
                products.add(product);
            }
            return products;
        } catch (SQLException ex) {
            throw new BadRequestException("Cannot run SQL statement: " + ex.getSQLState());
        }
    }

    @Override
    public Product queryOne(String sql) { // ОРМ ????
        try {
            Statement stmt = dbrepo.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                return new Product(
                        rs.getLong("product_id"),
                        rs.getString("product_name"),
                        rs.getString("product_type"),
                        rs.getInt("price")
                );
            }
        } catch (SQLException ex) {
            throw new BadRequestException("Cannot run SQL statement: " + ex.getMessage());
        }
        return null;
    }


    public Product getProductByID(long product_id) {
        String sql = "SELECT * FROM users WHERE id = " + product_id + " LIMIT 1";
        return queryOne(sql);
    }



    public Product getProductByName(String product_name) {
        try {
            String sql = "SELECT * FROM products WHERE product_name = ?";
            PreparedStatement stmt = dbrepo.getConnection().prepareStatement(sql);
            stmt.setString(1, product_name);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Product(
                        rs.getLong("product_id"),
                        rs.getString("product_name"),
                        rs.getString("product_type"),
                        rs.getInt("price")
                );
            }
        } catch (SQLException ex) {
            throw new BadRequestException("Cannot run SQL statement: " + ex.getMessage());
        }
        return null;
    }

    @Override
    public void remove(Product entity) {

    }



}
