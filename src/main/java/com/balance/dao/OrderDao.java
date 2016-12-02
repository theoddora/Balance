package com.balance.dao;

import com.balance.model.Order;

import java.util.List;
import java.util.Set;

/**
 * Created by hangelov on 01/12/2016.
 */
public interface OrderDao {

    void placeOrder(int productId, long userId, double amount);

    Set<Order> getAllOrders(long userId);

}
