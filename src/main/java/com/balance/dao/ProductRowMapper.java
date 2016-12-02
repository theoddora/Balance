package com.balance.dao;

import com.balance.model.Product;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;


@Component
public class ProductRowMapper implements RowMapper<Product> {

    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        Product product = new Product();
        product.setId(rs.getInt("id"));
        product.setName(rs.getString("name"));
        product.setProductType(rs.getString("type").trim());

        product.setIsForKilo(rs.getBoolean("is_for_kilo"));
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
