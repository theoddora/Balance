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

@SuppressWarnings("ALL")
@Component
public class ProductDaoImpl implements ProductDao {

    private DataSource dataSource;
    private NamedParameterJdbcTemplate jdbcTemplateObject;

    private static final String PRODUCT_TABLE_PROPERTIES = "id, name, type, amount_kg, amount_pc, price, discount, is_for_kilo, to_show";

    private static final String INSERT_PRODUCT =
        " INSERT INTO balance.product (name, type, amount_kg, amount_pc,price, discount,is_for_kilo) "
            + " VALUES (:name,:type,:amount_kg,:amount_pc,:price,:discount, :is_for_kilo )";

    private static final String DELETE_PRODUCT =
        "DELETE FROM product WHERE id = :id";

    private static final String REMOVE_PRODUCT =
        "UPDATE balance.product SET to_show=false WHERE product.id = :id";

    private static final String FIND_PRODUCTS_BY_ID =
        "SELECT" + PRODUCT_TABLE_PROPERTIES + "FROM product WHERE id = :id ";

    private static final String ALL_PRODUCTS_FOR_SALE =
        "UPDATE balance.product SET to_show=true WHERE product.id = :id";

    private static final String ALL_PRODUCTS_NOT_FOR_SALE =
        "SELECT" + PRODUCT_TABLE_PROPERTIES + "FROM product WHERE to_show = false";

    private static final String GET_ALL_PRODUCTS_FOR_SALE =
        "SELECT" + PRODUCT_TABLE_PROPERTIES + "FROM product WHERE to_show = true";

    private static final String GET_ALL_FRUITS =
        "SELECT" + PRODUCT_TABLE_PROPERTIES + "FROM balance.product WHERE product.type = 'fruit'";

    private static final String GET_ALL_VEGETABLES =
        "SELECT" + PRODUCT_TABLE_PROPERTIES + "FROM balance.product WHERE product.type = 'vegetable'";

    private static final String INCREASE_AMOUNT =
        "UPDATE balance.product  SET  amount_kg = amount_kg + :kilos WHERE product.id = :id";

    private static final String DECREASE_AMOUNT =
        "UPDATE balance.product  SET  amount_kg = amount_kg - :kilos WHERE product.id = :id";

    private static final String DECREASE_PRODUCT_BY_PRICE =
        "UPDATE balance.product  SET  amount_pc = amount_pc + :pieces WHERE product.id = :id";

    private static final String INCREASE_PRODUCT_BY_PRICE =
        "UPDATE balance.product  SET  amount_pc = amount_pc - :piece WHERE product.id = :id";

    private static final String UPDATE_PRODUCT =
        "UPDATE balance.product SET name = :name, type = :type, amount_kg = :amount_kg, amount_pc = :amount_pc, price = :price,"
            + "discount = :discount WHERE product.id = :id";

    private static final String GET_KG =
        "SELECT amount_kg FROM balance.product WHERE id = :id";
    private static final String GET_PC =
        "SELECT amount_pc FROM balance.product WHERE id = :id";

    private static final String GET_ALL_EMPTY_PRODUCTS =
        "SELECT" + PRODUCT_TABLE_PROPERTIES + "FROM balance.product WHERE "
            + " (amount_kg IS NULL AND amount_pc IS NULL) OR "
            + " (amount_kg = 0 AND amount_pc = 0) OR"
            + " (amount_kg IS NULL AND amount_pc = 0) OR"
            + " (amount_kg = 0 AND amount_pc IS NULL);";

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

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", product.getName());
        params.addValue("type", product.getProductType());
        params.addValue("amount_kg", product.getAmountKilo());
        params.addValue("amount_pc", product.getAmountPiece());
        params.addValue("price", product.getPrice());
        params.addValue("discount", product.getDiscount());
        params.addValue("is_for_kilo", product.getIsForKilo());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        getJdbcTemplate().update(INSERT_PRODUCT, params, keyHolder, new String[] { "id" });
        int id = keyHolder.getKey().intValue();
        product.setId(id);
        return product;
    }

    @Override
    public Product findProductById(int id) {

        Map<String, Object> params = new HashMap<>();
        params.put("id", id);

        Product product = getJdbcTemplate().queryForObject(FIND_PRODUCTS_BY_ID, params, new ProductRowMapper());
        product.setId(id);

        return product;
    }

    @Override
    public void deleteProduct(int id) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        getJdbcTemplate().update(DELETE_PRODUCT, params);
    }

    @Override
    public void removeProduct(int id) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        getJdbcTemplate().update(REMOVE_PRODUCT, params);
    }

    @Override
    public void addToTheStore(int id) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        getJdbcTemplate().update(ALL_PRODUCTS_FOR_SALE, params);
    }

    @Override
    public List<Product> getAllSellingProducts() {
        return getJdbcTemplate().query(GET_ALL_PRODUCTS_FOR_SALE, new ProductRowMapper());
    }

    @Override
    public List<Product> getAllProducts() {
        return null;
    }

    @Override
    public List<Product> getAllNotSellingProducts() {

        return getJdbcTemplate().query(ALL_PRODUCTS_NOT_FOR_SALE, new ProductRowMapper());
    }

    @Override
    public List<Product> getAllFruits() {
        return getJdbcTemplate().query(GET_ALL_FRUITS, new ProductRowMapper());
    }

    @Override
    public List<Product> getAllVegetables() {
        return getJdbcTemplate().query(GET_ALL_VEGETABLES, new ProductRowMapper());
    }

    @Override
    public int increaseProductByKilo(Double kilos, int id) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("kilos", kilos);
        getJdbcTemplate().update(INCREASE_AMOUNT, params);
        return id;
    }

    @Override
    public int decreaseProductByKilo(Double kilos, int id) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("kilos", kilos);
        getJdbcTemplate().update(DECREASE_AMOUNT, params);
        return id;
    }

    @Override
    public int increaseProductByPiece(Integer pieces, int id) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("pieces", pieces);
        getJdbcTemplate().update(INCREASE_PRODUCT_BY_PRICE, params);
        return id;
    }

    @Override
    public int decreaseProductByPiece(Integer piece, int id) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("piece", piece);
        getJdbcTemplate().update(DECREASE_PRODUCT_BY_PRICE, params);
        return id;
    }

    @Override
    public void updateProduct(int id, Product product) {

        Product product2 = findProductById(id);

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

        if (product.getAmountKilo() != null) {
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

        if (product.getDiscount() == 0.0) {
            params.put("discount", product2.getDiscount());
        } else {
            params.put("discount", product.getDiscount());
        }
        params.put("id", id);

        getJdbcTemplate().update(UPDATE_PRODUCT, params);
    }

    @Override
    public boolean hasEnoughAmount(double amount, int id, boolean isForKilo) {
        double result = this.getCurrentAmount(id, isForKilo);
        return result >= amount;
    }

    @Override
    public double getCurrentAmount(int id, boolean isForKilo) {

        String sql = isForKilo ? GET_KG : GET_PC;
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        return getJdbcTemplate().queryForObject(sql, params, Double.class);
    }

    @Override
    public List<Product> getAllEmptyProducts() {
        return getJdbcTemplate().query(GET_ALL_EMPTY_PRODUCTS, new ProductRowMapper());
    }

}
