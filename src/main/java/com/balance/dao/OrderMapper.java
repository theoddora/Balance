package com.balance.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.balance.model.Order;
import com.balance.model.Product;
import com.balance.model.User;

public class OrderMapper implements RowMapper<Order> {

    private ProductRowMapper productMapper;
    private UserMapper userMapper;

    public OrderMapper(ProductRowMapper mapper, UserMapper userMapper) {
        this.productMapper = mapper;
        this.userMapper = userMapper;
    }

    @Override
    public Order mapRow(ResultSet rs, int i) throws SQLException {

        Order order = new Order();
        order.setAmount(rs.getDouble("amount"));
        Product product = productMapper.mapRow(rs, i);
        User user = userMapper.mapRow(rs, i);
        order.setProduct(product);
        order.setUser(user);

        return order;
    }
}
