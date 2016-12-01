package com.balance.dao;

/**
 * Created by hangelov on 01/12/2016.
 */
public interface OrderDao {

    void placeOrder(int productId, int userId, double amount);

}
