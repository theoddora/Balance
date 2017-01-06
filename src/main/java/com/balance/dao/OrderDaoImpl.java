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
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Component;

import com.balance.model.Order;

@SuppressWarnings("ALL")
@Component
public class OrderDaoImpl implements OrderDao {

    private static final String SELECT_LAST_ORDER =
        "SELECT o.id, o.product_id,o.user_id, o.amount, p.amount_kg, p.amount_pc, "
            + "p.discount, p.id, p.name,p.price,p.is_for_kilo, p.type, u.user_id, u.name, "
            + "u.email, u.is_admin, u.password, u.username FROM balance.order o JOIN "
            + "balance.product p ON (p.id = o.product_id) JOIN balance.user u ON(u.user_id = o.user_id) "
            + " WHERE u.user_id = :userId AND o.id = :orderId";

    private static final String GET_ALL_ORDERS =
        "SELECT o.id, o.product_id,o.user_id, o.amount, p.amount_kg, p.amount_pc, p.discount, "
            + "p.id, p.name,p.price,p.is_for_kilo, p.type, u.user_id, u.name, u.email, u.is_admin, u.password, "
            + "u.username FROM balance.order o JOIN balance.product p ON (p.id = o.product_id) "
            + " JOIN balance.user u ON(u.user_id = o.user_id)  WHERE u.username = :username";

    private static final String PLACE_ORDER =
        "INSERT INTO balance.order (product_id, amount, user_Id) VALUES (:productId, :amount, :userId)";
    private NamedParameterJdbcTemplate jdbcTemplateObject;

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
        GeneratedKeyHolder holder = new GeneratedKeyHolder();
        getJdbcTemplate().update(PLACE_ORDER, params, holder, new String[] { "id" });
        return holder.getKey().longValue();
    }

    @Override
    public Set<Order> getAllOrders(String username) {

        Map<String, Object> params = new HashMap<>();
        params.put("username", username);

        List<Order> orders = getJdbcTemplate().query(GET_ALL_ORDERS, params, new OrderMapper(new ProductRowMapper(), new UserMapper()));
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

        return getJdbcTemplate().queryForObject(SELECT_LAST_ORDER, params, new OrderMapper(new ProductRowMapper(), new UserMapper()));
    }
}
