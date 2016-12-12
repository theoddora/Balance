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
import org.springframework.stereotype.Component;

import com.balance.model.Order;

/**
 * Created by hangelov on 01/12/2016.
 */
@Component
public class OrderDaoImpl implements OrderDao {

    private NamedParameterJdbcTemplate jdbcTemplateObject;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplateObject = new NamedParameterJdbcTemplate(dataSource);
    }

    public NamedParameterJdbcTemplate getJdbcTemplate() {
        return jdbcTemplateObject;
    }

    @Override
    public void placeOrder(int productId, long userId, double amount) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("productId", productId);
        params.addValue("amount", amount);
        params.addValue("userId", userId);
        String sqlOrder = "INSERT INTO balance.order (product_id, amount, user_Id) VALUES (:productId, :amount, :userId)";
        getJdbcTemplate().update(sqlOrder, params);
    }

    @Override
    public Set<Order> getAllOrders(long userId) {

        String sql = "SELECT o.id, o.product_id,o.user_id, o.amount, p.amount_kg, p.amount_pc, p.discount, p.id, p.name,p.price,p.is_for_kilo, p.type, u.user_id, u.name, u.email, u.is_admin, u.password, u.username FROM balance.order o JOIN balance.product p ON (p.id = o.product_id) JOIN balance.user u ON(u.user_id = o.user_id)  WHERE o.user_id = :userId";
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);

        List<Order> orders = getJdbcTemplate().query(sql, params, new OrderMapper(new ProductRowMapper(), new UserMapper()));
        Set<Order> uniqueOrders = new TreeSet<>();
        for (Order order : orders) {
            uniqueOrders.add(order);
        }

        return uniqueOrders;
    }
}
