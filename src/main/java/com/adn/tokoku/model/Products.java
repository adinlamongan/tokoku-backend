package com.adn.tokoku.model;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("products")
@NoArgsConstructor
public class Products {

    @Id
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
    private double harga;

}
