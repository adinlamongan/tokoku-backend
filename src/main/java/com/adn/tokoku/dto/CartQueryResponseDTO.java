package com.adn.tokoku.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CartQueryResponseDTO {
    private int id;
    private int productId;
    private String nama;
    private String size;
    private String color;
    private double harga;
    private double qty;
    private double stock;
    private double total;
    private String image;
}
