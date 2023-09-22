package com.adn.tokoku.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("product_stok")
@NoArgsConstructor
public class ProductStok {

    @Id
    private int id;
    private int productId;
    private String color;
    private String size;
    private double stock;

}
