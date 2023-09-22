package com.adn.tokoku.repository;

import com.adn.tokoku.dto.CartQueryResponseDTO;
import com.adn.tokoku.model.CartH;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartHRepo extends CrudRepository<CartH, Integer> {

    @Query("SELECT d.id,d.cart_id, ps.product_id,p.nama,d.harga,d.qty,PI.image, "
            + "h.user_id,h.negara,h.provinsi,h.provinsi,h.kota,h.kode_pos,h.alamat,h.pengirim_id,h.biaya_pengiriman,h.total,h.bayar, "
            + "mp.nama AS jasa_pengirim FROM cart_h h "
            + "JOIN cart_d d ON d.cart_id=h.id "
            + "JOIN product_stok ps ON ps.id=d.stok_id "
            + "JOIN products p ON p.id=ps.product_id "
            + "JOIN product_image PI ON PI.product_id=p.id "
            + "LEFT JOIN master_pengirim mp ON mp.id=h.pengirim_id "
            + "WHERE PI.is_primary=TRUE "
            + "AND d.cart_id=:cartId and h.bayar=:bayar")
    List<CartQueryResponseDTO> findByCartIdAndBayar(@Param("cartId") int cartId, @Param("bayar") Boolean bayar);
}
