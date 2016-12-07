package com.balance.model;

/**
 * Created by hangelov on 21/11/2016.
 */
public class Product implements Comparable<Product> {

    private Integer id;
    private String productType;
    private String name;
    private Double amountKilo;
    private Integer amountPiece;
    private Double price;
    private double discount;
    private boolean isForKilo;

    public Product(String productType, String name, Double amountKilo, Integer amountPiece, Double price, double discount, boolean isForKilo) {
        this.name = name;
        this.amountKilo = amountKilo;
        this.amountPiece = amountPiece;
        this.price = price;
        this.discount = discount;
        this.id = id;
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

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
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

    public Double getAmountKilo() {
        return amountKilo;
    }

    public void setAmountKilo(Double amountKilo) {
        this.amountKilo = amountKilo;
    }

    public Integer getAmountPiece() {
        return amountPiece;
    }

    public void setAmountPiece(Integer amountPiece) {
        this.amountPiece = amountPiece;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{"
            + "id=" + id
            + ", productType=" + productType
            + ", name='" + name + '\''
            + ", amountKilo=" + amountKilo
            + ", amountPiece=" + amountPiece
            + ", price=" + price
            + ", discount=" + discount
            + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        return id.equals(product.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public int compareTo(Product o) {
        if (name.compareTo(o.getName()) == 0) {
            return price.compareTo(o.getPrice());
        }
        return name.compareTo(o.getName());
    }
}
