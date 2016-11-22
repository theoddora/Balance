package com.balance.dao;

import com.balance.model.Product;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by hangelov on 22/11/2016.
 */
public class ProductDaoImpl implements ProductDao{


    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplateObject;
    }

    @Override
    public void insertProduct(Product product) {



    }

    @Override
    public Product findProductById(int id) {

        String sql = "Select * from product where product.id = ? ";

        Product product = (Product)getJdbcTemplate().queryForObject(
                sql, new Object[] { id },
                new ProductRowMapper());
        return product;
    }

    @Override
    public void deleteProduct(int id) {

    }

    @Override
    public List<Product> getAllProducts() {
        return null;
    }
}
