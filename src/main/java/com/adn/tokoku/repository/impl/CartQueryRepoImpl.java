package com.adn.tokoku.repository.impl;

import com.adn.tokoku.dto.CartQueryResponseDTO;
import com.adn.tokoku.repository.CartQueryRepo;
import org.springframework.stereotype.Repository;

@Repository
public class CartQueryRepoImpl implements CartQueryRepo {
    @Override
    public CartQueryResponseDTO findByCartId(int CartId, Boolean bayar) {
        return null;
    }
}
