package com.adn.tokoku.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("cart_d")
@NoArgsConstructor
public class CartD {

    @Id
    private int id;
    private int cartId;
    private int stokId;
    private Number qty;
    private Number harga;

}
