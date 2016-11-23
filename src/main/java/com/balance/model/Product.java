package com.balance.model;

/**
 * Created by hangelov on 21/11/2016.
 */
public class Product {
    private int id;
    private ProductType productTupe;
    private String name;
    private double amountKilo;
    private int amountPiece;
    private double price;
    private double discount;



    public Product(int id, ProductType productTupe, String name, double amountKilo, int amountPiece, double price, double discount) {
        this.id = id;
        this.productTupe = productTupe;
        this.name = name;
        this.amountKilo = amountKilo;
        this.amountPiece = amountPiece;
        this.price = price;
        this.discount = discount;

    }

    public Product() {

    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ProductType getProductTupe() {
        return productTupe;
    }

    public void setProductTupe(ProductType productTupe) {
        this.productTupe = productTupe;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAmountKilo() {
        return amountKilo;
    }

    public void setAmountKilo(double amountKilo) {
        this.amountKilo = amountKilo;
    }

    public double getAmountPiece() {
        return amountPiece;
    }

    public void setAmountPiece(int amountPiece) {
        this.amountPiece = amountPiece;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


}
