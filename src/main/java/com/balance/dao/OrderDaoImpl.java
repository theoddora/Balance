package com.balance.dao;

import com.balance.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.*;

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

        String sqlOrder = "INSERT INTO balance.order (product_id, amount, user_Id) VALUES (:productId, :amount, :userId)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("productId", productId);
        params.addValue("amount", amount);
        params.addValue("userId", userId);


        getJdbcTemplate().update(sqlOrder, params);

    }

    @Override
    public Set<Order> getAllOrders(long userId) {

        String sql = "SELECT o.id, o.product_id,o.user_id, o.amount, p.amount_kg, p.amount_pc, p.discount, p.id, p.name,p.price,p.is_for_kilo, p.type, u.user_id, u.name, u.email, u.is_admin, u.password, u.username FROM balance.order o JOIN balance.product p ON (p.id = o.product_id) JOIN balance.user u ON(u.user_id = o.user_id)  WHERE o.user_id = :userId";
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);

        List<Order> orders = getJdbcTemplate().query(sql, params, new OrderMapper(new ProductRowMapper(), new UserMapper()));
        Set<Order> uniqueOrders = new HashSet<>();
        for (Order order : orders) {
            uniqueOrders.add(order);
        }

        return uniqueOrders;
    }
}
