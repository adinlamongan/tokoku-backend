package com.adn.tokoku.dto;


import com.adn.tokoku.model.ProductColor;
import com.adn.tokoku.model.ProductImage;
import com.adn.tokoku.model.ProductSize;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.util.List;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ProductDetailResponseDTO {

    private int id;
    private String nama;
    private String deskripsi;
    private Boolean men=false;
    private Boolean women=false;
    private int kategoriId;
    private Number weight;
    private Number panjang;
    private Number lebar;
    private Number pl;
    private String materials;
    private Number harga;

    private List<ProductImage> images;
    private List<ProductSize> sizes;
    private List<ProductColor> colors;
    
}
