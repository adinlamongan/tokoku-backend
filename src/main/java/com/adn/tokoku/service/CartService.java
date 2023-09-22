package com.adn.tokoku.service;

import com.adn.tokoku.dto.CartQueryResponseDTO;
import com.adn.tokoku.dto.CartRequestDTO;

import java.util.List;

public interface CartService {
    void createUpdateCart(CartRequestDTO dto);

    Number getQty();

    List<CartQueryResponseDTO> findCart();
}
