package com.balance.dao;

import com.balance.model.Product;
import com.balance.model.ProductType;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by hangelov on 22/11/2016.
 */
@Component
public class ProductRowMapper implements RowMapper<Product> {

    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        Product product = new Product();
        product.setId(rs.getInt("id"));
        product.setName(rs.getString("name"));
        ProductType prodType = ProductType.valueOf(rs.getString("type").trim());
        System.out.println(prodType);
        product.setProductTupe(prodType);
        double amountKilo = rs.getDouble("amount_kg");
        if( amountKilo > 0){
            product.setAmountKilo(amountKilo);
        }

        int amountPiece = rs.getInt("amount_pc");
        if(amountPiece > 0){
            product.setAmountPiece(amountPiece);
        }

        product.setPrice(rs.getDouble("price"));
        double discount = rs.getDouble("discount");

        if(discount > 0){
            product.setDiscount(discount);
        }


        return product;
    }


}
