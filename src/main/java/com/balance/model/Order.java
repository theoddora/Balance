package com.balance.model;

/**
 * Created by hangelov on 28/11/2016.
 */
public class Order {

    private User user;
    private double amount;
    private Product product;

    public Order(){

    }

    public Order(User user, double amount, Product product) {
        this.user = user;
        this.amount = amount;
        this.product = product;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (!user.equals(order.user)) return false;
        return product.equals(order.product);

    }

    @Override
    public int hashCode() {
        int result = user.hashCode();
        result = 31 * result + product.hashCode();
        return result;
    }
}
