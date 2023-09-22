package com.adn.tokoku.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("cart_h")
@NoArgsConstructor
public class CartH {

    @Id
    private int id;
    private int userId;
    private int productId;
    private Number qty;
    private Number harga;
    private String negara;
    private String provinsi;
    private String kota;
    private String kode_pos;
    private String alamat;
    private int pengirim_id;
    private Number biaya_pengiriman;
    private Number total;
    private Boolean bayar = false;

}
