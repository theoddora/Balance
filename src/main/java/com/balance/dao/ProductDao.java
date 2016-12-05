package com.balance.dao;

import com.balance.model.Product;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.access.annotation.Secured;

import javax.sql.DataSource;
import java.util.List;


public interface ProductDao {

    @Secured({"IS_AUTHENTICATED_ANONYMOUSLY"})
    void setDataSource(DataSource ds);

    @CacheEvict(value = "productCache" , allEntries = true)
    @Cacheable(value = "productCache")
    Product insertProduct(Product product);

    @Cacheable(value = "productCache", key = "#root.args[0]")
    Product findProductById(int id);

    @CacheEvict(value = "productCache")
    void deleteProduct(int id);

    @Cacheable(value = "productCache")
    List<Product> getAllProducts();

    @Cacheable(value = "productCache")
    List<Product> getAllFruits();

    @Cacheable(value = "productCache")
    List<Product> getAllVegetables();

    @CacheEvict(value = "productCache",key = "#result")
    int increaseProductByKilo(Double kilos, int id);

    @CacheEvict(value = "productCache",key = "#result")
    int decreaseProductByKilo(Double kilos, int id);

    @CacheEvict(value = "productCache",key = "#result")
    int increaseProductByPiece(Integer pieces, int id);

    @CacheEvict(value = "productCache",key = "#result")
    int decreaseProductByPiece(Integer piece, int id);

    boolean hasEnoughAmount(double amount, int id, boolean isForKilo);

    double getCurrentAmount(int id, boolean isForKilo);


}
