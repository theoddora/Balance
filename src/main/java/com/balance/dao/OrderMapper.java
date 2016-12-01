package com.balance.dao;


import com.balance.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by hangelov on 01/12/2016.
 */
public class OrderMapper implements RowMapper<Order> {

    @Autowired
    ProductDao productDao;



    @Override
    public Order mapRow(ResultSet rs, int i) throws SQLException {

        Order order  = new Order();
        order.setAmount(rs.getDouble("amount"));
        int productid = rs.getInt("product_id");
        order.setProduct(productDao.findProductById(productid));
        return order;
    }
}
