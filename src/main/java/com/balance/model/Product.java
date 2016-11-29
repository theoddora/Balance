package com.balance.model;

/**
 * Created by hangelov on 21/11/2016.
 */
public class Product {
    private int id;
    private String productType;
    private String name;
    private double amountKilo;
    private double amountPiece;
    private double price;
    private double discount;
    private boolean isForKilo;



   
    public Product( String productType, String name, double amountKilo, double amountPiece, double price, double discount, boolean isForKilo) {

        
        this.name = name;
        this.amountKilo = amountKilo;
        this.amountPiece = amountPiece;
        this.price = price;
        this.discount = discount;
        this.isForKilo = isForKilo;

    }

    public Product() {

    }

    public boolean getIsForKilo() {
        return isForKilo;
    }

    public void setIsForKilo(boolean isForKilo) {
        this.isForKilo = isForKilo;
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

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
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

    public void setAmountPiece(double amountPiece) {
        this.amountPiece = amountPiece;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productType=" + productType +
                ", name='" + name + '\'' +
                ", amountKilo=" + amountKilo +
                ", amountPiece=" + amountPiece +
                ", price=" + price +
                ", discount=" + discount +
                '}';
    }
}
