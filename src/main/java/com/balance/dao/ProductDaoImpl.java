package com.balance.dao;

import com.balance.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

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
        params.put("type", product.getProductTupe().toString());
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



    }

    @Override
    public List<Product> getAllProducts() {


        String sql = "Select * from product";
        List<Product> products = getJdbcTemplate().query(sql, new ProductRowMapper());

        return products;
    }
}
