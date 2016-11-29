package com.balance.dao;

import com.balance.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hangelov on 22/11/2016.
 */
@Component
public class ProductDaoImpl implements ProductDao {


    private DataSource dataSource;
    private NamedParameterJdbcTemplate jdbcTemplateObject;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new NamedParameterJdbcTemplate(dataSource);
    }

    public NamedParameterJdbcTemplate getJdbcTemplate() {
        return jdbcTemplateObject;
    }

    @Override
    public void insertProduct(Product product) {
        String sql = " insert into balance.product (name, type, amount_kg, amount_pc,price, discount) values (:name,:type,:amount_kg,:amount_pc,:price,:discount )";

        Map<String, Object> params = new HashMap<>();
        params.put("name", product.getName());
        params.put("type", product.getProductType().toString());
        params.put("amount_kg", product.getAmountKilo());
        params.put("amount_pc", product.getAmountPiece());
        params.put("price", product.getPrice());
        params.put("discount", product.getDiscount());


        getJdbcTemplate().update(sql, params);


    }

    @Override
    public Product findProductById(int id) {


        String sql = "Select * from product where product.id = :id ";

        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        Product product = null;
        List<Product> prods = getJdbcTemplate().query(sql, params, new ProductRowMapper());
        if (prods.size() > 0) {
            product = prods.get(0);
        }
        return product;
    }


    @Override
    public void deleteProduct(int id) {
        String sql = "delete from product * where id = :id";
        Map<String , Object> params = new HashMap<>();
        params.put("id", id);
        getJdbcTemplate().update(sql, params);


    }

    @Override
    public List<Product> getAllProducts() {

        String sql = "Select * from product";
        List<Product> products = getJdbcTemplate().query(sql, new ProductRowMapper());

        return products;
    }

    @Override
    public List<Product> getAllFruits(){
        String sql = "SELECT * FROM balance.product WHERE product.type = 'fruit'";
        List<Product> products = getJdbcTemplate().query(sql, new ProductRowMapper());

        return products;
    }

    @Override
    public List<Product> getAllVegetables(){
        String sql = "SELECT * FROM balance.product WHERE product.type = 'vegetable'";
        List<Product> products = getJdbcTemplate().query(sql, new ProductRowMapper());

        return products;
    }

    @Override
    public void increaseProductByKilo(double kilos, int id) {
        String sql = "update balance.product  SET  amount_kg = amount_kg + :kilos where product.id = :id";
        Map<String , Object> params = new HashMap<>();
        params.put("id", id);
        params.put("kilos", kilos);
        getJdbcTemplate().update(sql, params);
    }

    @Override
    public void decreaseProductByKilo(double kilos,int id) {
        String sql = "update balance.product  SET  amount_kg = amount_kg - :kilos where product.id = :id";
        Map<String , Object> params = new HashMap<>();
        params.put("id", id);
        params.put("kilos", kilos);
        getJdbcTemplate().update(sql, params);
    }

    @Override
    public void increaseProductByPiece(double pieces, int id) {
        String sql = "update balance.product  SET  amount_pc = amount_pc + :pieces where product.id = :id";
        Map<String , Object> params = new HashMap<>();
        params.put("id", id);
        params.put("pieces", pieces);
        getJdbcTemplate().update(sql, params);

    }

    @Override
    public void decreaseProductByPiece(double piece, int id) {
        String sql = "update balance.product  SET  amount_pc = amount_pc - :piece where product.id = : id";
        Map<String , Object> params = new HashMap<>();
        params.put("id", id);
        params.put("piece", piece);
        getJdbcTemplate().update(sql, params);

    }


}
