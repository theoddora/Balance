package com.balance.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Component;

import com.balance.model.Order;

/**
 * Created by hangelov on 01/12/2016.
 */
@Component
public class OrderDaoImpl implements OrderDao {

    private static final String SELECT_LAST_ORDER =
        "SELECT o.id, o.product_id,o.user_id, o.amount, p.amount_kg, p.amount_pc, p.discount, p.id, p.name,p.price,p.is_for_kilo, p.type, u.user_id, u.name, u.email, u.is_admin, u.password, u.username FROM balance.order o JOIN balance.product p ON (p.id = o.product_id) JOIN balance.user u ON(u.user_id = o.user_id)  WHERE u.user_id = :userId AND o.id = :orderId";
    private NamedParameterJdbcTemplate jdbcTemplateObject;
    private SimpleJdbcInsert insert;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplateObject = new NamedParameterJdbcTemplate(dataSource);
    }

    public NamedParameterJdbcTemplate getJdbcTemplate() {
        return jdbcTemplateObject;
    }

    @Override
    public long placeOrder(int productId, long userId, double amount) {

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("productId", productId);
        params.addValue("amount", amount);
        params.addValue("userId", userId);
        String sqlOrder = "INSERT INTO balance.order (product_id, amount, user_Id) VALUES (:productId, :amount, :userId)";
        GeneratedKeyHolder holder = new GeneratedKeyHolder();
        getJdbcTemplate().update(sqlOrder, params, holder, new String[] { "id" });
        long orderId = holder.getKey().longValue();
        return orderId;
    }

    @Override
    public Set<Order> getAllOrders(String username) {

        String sql = "SELECT o.id, o.product_id,o.user_id, o.amount, p.amount_kg, p.amount_pc, p.discount, "
            + "p.id, p.name,p.price,p.is_for_kilo, p.type, u.user_id, u.name, u.email, u.is_admin, u.password, "
            + "u.username FROM balance.order o JOIN balance.product p ON (p.id = o.product_id) "
            + " JOIN balance.user u ON(u.user_id = o.user_id)  WHERE u.username = :username";
        Map<String, Object> params = new HashMap<>();
        params.put("username", username);

        List<Order> orders = getJdbcTemplate().query(sql, params, new OrderMapper(new ProductRowMapper(), new UserMapper()));
        Set<Order> uniqueOrders = new TreeSet<>();
        for (Order order : orders) {
            uniqueOrders.add(order);
        }

        return uniqueOrders;
    }

    @Override
    public Order getLastOrder(long userId, long orderId) {

        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        params.put("orderId", orderId);

        Order order =
            getJdbcTemplate().queryForObject(SELECT_LAST_ORDER, params, new OrderMapper(new ProductRowMapper(), new UserMapper()));

        return order;
    }
}
