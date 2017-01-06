package com.balance.model;

public class Order implements Comparable<Order> {

    private User user;
    private double amount;
    private Product product;
    private Order o;

    public Order() {

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
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Order order = (Order) o;

        return user.equals(order.user) && product.equals(order.product);

    }

    @Override
    public int hashCode() {
        int result = user.hashCode();
        result = 31 * result + product.hashCode();
        return result;
    }

    @Override
    public int compareTo(Order o) {
        return product.compareTo(o.getProduct());
    }
}
