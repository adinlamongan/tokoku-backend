package com.adn.tokoku.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("product_size")
@NoArgsConstructor
public class ProductSize {
    @Id
    private int id;
    private int productId;
    private String size;
}
