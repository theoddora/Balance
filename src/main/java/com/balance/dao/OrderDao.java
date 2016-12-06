package com.balance.dao;

import java.util.Set;

import com.balance.model.Order;

/**
 * Created by hangelov on 01/12/2016.
 */
public interface OrderDao {

    void placeOrder(int productId, long userId, double amount);

    Set<Order> getAllOrders(long userId);

}
