package com.balance.dao;

import com.balance.model.Product;
import org.springframework.security.access.annotation.Secured;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by hangelov on 22/11/2016.
 */
public interface ProductDao {

    @Secured({"IS_AUTHENTICATED_ANONYMOUSLY"})
    void setDataSource(DataSource ds);

    void insertProduct(Product product);

    Product findProductById(int id);


    void deleteProduct(int id);

    List<Product> getAllProducts();

    List<Product> getAllFruits();

    List<Product> getAllVegetables();

    void increaseProductByKilo(double kilos, int id);

    void decreaseProductByKilo(double kilos, int id);

    void increaseProductByPiece(double pieces, int id);

    void decreaseProductByPiece(double piece, int id);

    boolean hasEnoughAmount(double amount, int id, boolean isForKilo);

    double getCurrentAmount(int id, boolean isForKilo);




}
