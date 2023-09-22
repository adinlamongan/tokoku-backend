package com.adn.tokoku.repository;

import com.adn.tokoku.dto.ProductListResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface WishlistQueryRepo {
    Page<ProductListResponseDTO> findProducts(int userId, Pageable pageable);
}
