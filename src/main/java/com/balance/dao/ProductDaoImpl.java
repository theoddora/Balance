package com.balance.dao;

import com.balance.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
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
    public Product insertProduct(Product product) {
        String sql = " INSERT INTO balance.product (name, type, amount_kg, amount_pc,price, discount,is_for_kilo) VALUES (:name,:type,:amount_kg,:amount_pc,:price,:discount, :is_for_kilo )";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource params = new MapSqlParameterSource();
//        Map<String, Object> params = new HashMap<>();
        params.addValue("name", product.getName());
        params.addValue("type", product.getProductType().toString());
        params.addValue("amount_kg", product.getAmountKilo());
        params.addValue("amount_pc", product.getAmountPiece());
        params.addValue("price", product.getPrice());
        params.addValue("discount", product.getDiscount());
        params.addValue("is_for_kilo", product.getIsForKilo());


        getJdbcTemplate().update(sql, params, keyHolder, new String[]{"id"});
        int id = keyHolder.getKey().intValue();
        product.setId(id);
        return product;


    }

    @Override
    public Product findProductById(int id) {


        String sql = "SELECT * FROM product WHERE id = :id ";

        Map<String, Object> params = new HashMap<>();
        params.put("id", id);

        Product product = getJdbcTemplate().queryForObject(sql, params, new ProductRowMapper());
        product.setId(id);

        return product;
    }


    @Override
    public void deleteProduct(int id) {
        String sql = "DELETE FROM product * WHERE id = :id";
        Map<String, Object> params = new HashMap<>();
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
    public List<Product> getAllFruits() {
        String sql = "SELECT * FROM balance.product WHERE product.type = 'fruit'";
        List<Product> products = getJdbcTemplate().query(sql, new ProductRowMapper());

        return products;
    }

    @Override
    public List<Product> getAllVegetables() {
        String sql = "SELECT * FROM balance.product WHERE product.type = 'vegetable'";
        List<Product> products = getJdbcTemplate().query(sql, new ProductRowMapper());

        return products;
    }


    @Override
    public int increaseProductByKilo(Double kilos, int id) {
        String sql = "UPDATE balance.product  SET  amount_kg = amount_kg + :kilos WHERE product.id = :id";
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("kilos", kilos);
        getJdbcTemplate().update(sql, params);
        return id;
    }

    @Override
    public int decreaseProductByKilo(Double kilos, int id) {
        String sql = "UPDATE balance.product  SET  amount_kg = amount_kg - :kilos WHERE product.id = :id";
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("kilos", kilos);
        getJdbcTemplate().update(sql, params);
        return id;
    }

    @Override
    public int increaseProductByPiece(Integer pieces, int id) {
        String sql = "UPDATE balance.product  SET  amount_pc = amount_pc + :pieces WHERE product.id = :id";
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("pieces", pieces);
        getJdbcTemplate().update(sql, params);
        return id;

    }

    @Override
    public int decreaseProductByPiece(Integer piece, int id) {
        String sql = "UPDATE balance.product  SET  amount_pc = amount_pc - :piece WHERE product.id = :id";
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("piece", piece);
        getJdbcTemplate().update(sql, params);
        return id;
    }

    @Override
    public boolean hasEnoughAmount(double amount, int id, boolean isForKilo) {

        double result = this.getCurrentAmount(id, isForKilo);
        if (result < amount) {
            return false;
        } else {
            return true;
        }

    }

    @Override
    public double getCurrentAmount(int id, boolean isForKilo) {
        String sql = null;
        if (isForKilo) {
            sql = "SELECT amount_kg FROM balance.product WHERE id = :id";
        } else {
            sql = "SELECT amount_pc FROM balance.product WHERE id = :id";
        }

        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        double result = getJdbcTemplate().queryForObject(sql, params, Double.class);

        return result;
    }


}
