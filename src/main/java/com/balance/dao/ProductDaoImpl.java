package com.balance.dao;

import com.balance.model.Product;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by hangelov on 22/11/2016.
 */
@Component
public class ProductDaoImpl implements ProductDao{


    private DataSource dataSource;
    private NamedParameterJdbcTemplate jdbcTemplateObject;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new NamedParameterJdbcTemplate(dataSource);
    }

    public NamedParameterJdbcTemplate getJdbcTemplate() {
        return jdbcTemplateObject;
    }

    @Override
    public void insertProduct(Product product) {



    }

    @Override
    public Product findProductById(int id) {

        String sql = "Select * from product where product.id = :id ";

        Product product = (Product)getJdbcTemplate().query(
                sql,
                new ProductRowMapper());
        return product;
    }

    @Override
    public void deleteProduct(int id) {

        String sql = "delete * from product where id = :id";





    }

    @Override
    public List<Product> getAllProducts() {


        String sql = "Select * from product";
        List<Product>products = getJdbcTemplate().query(sql, new ProductRowMapper());

        return products;
    }
}
