package com.adn.tokoku.repository;

import com.adn.tokoku.dto.CartQueryResponseDTO;

public interface CartQueryRepo {
    CartQueryResponseDTO findByCartId(int CartId, Boolean bayar);
}
