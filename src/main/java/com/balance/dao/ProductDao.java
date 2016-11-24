package com.balance.dao;

import com.balance.model.Product;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by hangelov on 22/11/2016.
 */
public interface ProductDao {

    void setDataSource(DataSource ds);

    void insertProduct(Product product);

    Product findProductById(int id);

    void deleteProduct(int id);

    List<Product> getAllProducts();

    void increseProductByKilo(double kilos, int id);

    void decreaseProductByKilo(double kilos, int id);

    void increaseProductByPiece(int pieces, int id);

    void decreaseProductByPiece(int piece, int id);







}
