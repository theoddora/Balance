package com.balance.service;

import com.balance.dao.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by hangelov on 01/12/2016.
 */
public class OrderManagerImpl implements OrderManager {

    @Autowired
    private OrderDao orderDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void placeOrder(int productId, int userId, double amount) {

        orderDao.placeOrder(productId, userId,amount);
    }
}
