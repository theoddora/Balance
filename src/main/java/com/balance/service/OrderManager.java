package com.balance.service;

import java.util.Set;

import com.balance.model.Order;

/**
 * Created by hangelov on 01/12/2016.
 */
public interface OrderManager {

    void placeOrder(int productId, long userId, double amount);

    Set<Order> getOrders(long userId);
}
