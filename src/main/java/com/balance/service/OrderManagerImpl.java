package com.balance.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.balance.dao.OrderDao;
import com.balance.model.Order;

/**
 * Created by hangelov on 01/12/2016.
 */
@Component
public class OrderManagerImpl implements OrderManager {

    @Autowired
    private OrderDao orderDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public long placeOrder(int productId, long userId, double amount) {

        return orderDao.placeOrder(productId, userId,amount);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Set<Order> getOrders(String username) {
        return orderDao.getAllOrders(username);
    }

    @Override
    public Order getLastOrder(long userId, long orderId) {
        return orderDao.getLastOrder(userId, orderId);
    }
}
