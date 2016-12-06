package com.balance.dao;

import com.balance.model.Product;
import org.springframework.security.access.annotation.Secured;

import javax.print.attribute.IntegerSyntax;
import javax.sql.DataSource;
import java.util.List;


public interface ProductDao {

    @Secured({"IS_AUTHENTICATED_ANONYMOUSLY"})
    void setDataSource(DataSource ds);

    void insertProduct(Product product);

    Product findProductById(int id);


    void removeProduct(int id);

    List<Product> getAllSellingProducts();

    List<Product> getAllNotSellingProducts();

    List<Product> getAllFruits();

    List<Product> getAllVegetables();

    List<Product> getAllEmptyProducts();

    void updateProduct(int id, Product product);

    void addToTheStore(int id);

    void increaseProductByKilo(Double kilos, int id);

    void decreaseProductByKilo(Double kilos, int id);

    void increaseProductByPiece(Integer pieces, int id);

    void decreaseProductByPiece(Integer piece, int id);

    boolean hasEnoughAmount(double amount, int id, boolean isForKilo);

    double getCurrentAmount(int id, boolean isForKilo);




}
