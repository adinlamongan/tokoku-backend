package com.adn.tokoku.repository;

import com.adn.tokoku.dto.CartQueryResponseDTO;
import com.adn.tokoku.model.Cart;
import com.adn.tokoku.model.CartH;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CartRepo extends CrudRepository<Cart, Integer> {

    @Query("SELECT c.id,c.stok_id,c.qty, ps.product_id,p.nama,p.harga,c.qty,PI.image,ps.size,ps.color, c.qty * p.harga as total,ps.stock  "
            + "FROM cart c "
            + "JOIN product_stok ps ON ps.id=c.stok_id "
            + "JOIN products p ON p.id=ps.product_id "
            + "JOIN product_image PI ON PI.product_id=p.id "
            + "WHERE PI.is_primary=TRUE AND c.user_id=:userId")
    List<CartQueryResponseDTO> queryFindByUserId(@Param("userId") int userId);

    Cart findByUserIdAndStokId(int userId, int stokId);

    @Query("SELECT SUM(qty) AS total_qty FROM cart WHERE user_id=:userId ")
    Number getTotalQty(@Param("userId") int userId);
}
