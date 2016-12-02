package com.balance.service;

import com.balance.model.Order;

import java.util.List;
import java.util.Set;

/**
 * Created by hangelov on 01/12/2016.
 */
public interface OrderManager {

   void placeOrder(int productId, long userId, double amount);


    Set<Order> getOrders(long userId);
}
