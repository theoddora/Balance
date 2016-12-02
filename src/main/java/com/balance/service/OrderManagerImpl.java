package com.balance.service;

import com.balance.dao.OrderDao;
import com.balance.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/**
 * Created by hangelov on 01/12/2016.
 */
@Component
public class OrderManagerImpl implements OrderManager {

    @Autowired
    private OrderDao orderDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void placeOrder(int productId, long userId, double amount) {

        orderDao.placeOrder(productId, userId,amount);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Set<Order> getOrders(long userId) {


        return orderDao.getAllOrders(userId);
    }
}
