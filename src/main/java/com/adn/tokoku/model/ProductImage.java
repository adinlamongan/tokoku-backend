package com.adn.tokoku.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("product_image")
@NoArgsConstructor
public class ProductImage {
    @Id
    private int id;
    private int productId;
    private String image;
    private Boolean isPrimary=false;
}
