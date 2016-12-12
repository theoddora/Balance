package com.balance.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import com.balance.model.Product;

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
    public void removeProduct(int id){
            String sql = "UPDATE balance.product SET to_show=false WHERE product.id = :id";
            Map<String, Object> params = new HashMap<>();
            params.put("id", id);
            getJdbcTemplate().update(sql, params);
        }

    @Override
    public void addToTheStore(int id) {
        String sql = "UPDATE balance.product SET to_show=true WHERE product.id = :id";
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        getJdbcTemplate().update(sql, params);

    }

    @Override
    public List<Product> getAllSellingProducts() {

        String sql = "SELECT * FROM product WHERE to_show = true";
        List<Product> products = getJdbcTemplate().query(sql, new ProductRowMapper());

        return products;
    }

    @Override
    public List<Product> getAllProducts() {
        return null;
    }

    @Override
    public List<Product> getAllNotSellingProducts() {

        String sql = "SELECT * FROM product WHERE to_show = false";
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
    public void updateProduct(int id, Product product) {

        Product product2 = findProductById(id);

        String sql = "UPDATE balance.product SET name = :name, type = :type, amount_kg = :amount_kg, amount_pc = :amount_pc, price = :price," +
                "discount = :discount WHERE product.id = :id";
        Map<String, Object> params = new HashMap<>();

        if (product.getName() != null && !product.getName().isEmpty()) {
            params.put("name", product.getName());
        } else {
            params.put("name", product2.getName());
        }

        if (product.getProductType() != null && !product.getProductType().isEmpty()) {
            params.put("type", product.getProductType());
        } else {
            params.put("type", product2.getProductType());
        }

        if ( product.getAmountKilo() != null) {
            params.put("amount_kg", product.getAmountKilo());
        } else {
            params.put("amount_kg", product2.getAmountKilo());
        }

        if (product.getAmountPiece() != null && product.getAmountPiece() != 0) {
            params.put("amount_pc", product.getAmountPiece());
        } else {
            params.put("amount_pc", product2.getAmountPiece());
        }

        if (product.getPrice() != null && product.getPrice() != 0) {
            params.put("price", product.getPrice());
        } else {
            params.put("price", product2.getPrice());
        }

        if(product.getDiscount() == 0.0){
            params.put("discount", product2.getDiscount());
        }else {
            params.put("discount", product.getDiscount());
        }
        params.put("id", id);

        getJdbcTemplate().update(sql, params);

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

    @Override
    public List<Product> getAllEmptyProducts(){
        String sql = "SELECT * FROM balance.product WHERE " +
                " (amount_kg IS NULL AND amount_pc IS NULL) OR " +
                " (amount_kg = 0 AND amount_pc = 0) OR" +
                " (amount_kg IS NULL AND amount_pc = 0) OR" +
                " (amount_kg = 0 AND amount_pc IS NULL);";
        List<Product> products = getJdbcTemplate().query(sql, new ProductRowMapper());
        return products;
    }


}
