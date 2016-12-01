package com.balance.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hangelov on 01/12/2016.
 */
public class OrderDaoImpl  implements OrderDao {

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
    public void placeOrder(int productId, int userId, double amount) {

        String sqlOrder = "insert into order (product_id, amount) values (:productId, :amount)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        Map<String , Object> params = new HashMap<>();
        params.put("productId", productId);
        params.put("amount", amount);

        int orderId = getJdbcTemplate().update(sqlOrder, (SqlParameterSource) params,keyHolder);

        String sqlMapOrder = "insert into order_user (order_id, user_id ) values (:orderId, :userId)";
        Map<String , Object> mapParams = new HashMap<>();
        mapParams.put("orderId", orderId);
        mapParams.put("userId", userId);

        getJdbcTemplate().update(sqlMapOrder, mapParams);
    }
}
