package com.adn.tokoku.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("cart")
@NoArgsConstructor
public class Cart {

    @Id
    private int id;
    private int userId;
    private int stokId;
    private Number qty;
    private Number harga;

}
