package com.adn.tokoku.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("product_review")
@NoArgsConstructor
public class ProductReview {
    @Id
    private int id;
    private int userId;
    private int productId;
    private String review;
    private int invoiceId;
}
