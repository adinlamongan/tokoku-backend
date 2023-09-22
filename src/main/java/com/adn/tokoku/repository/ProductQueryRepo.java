package com.adn.tokoku.repository;

import com.adn.tokoku.dto.ProductListResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductQueryRepo {
    Page<ProductListResponseDTO> findProducts(String nama, String color, Number hargaMulai, Number hargaSampai, String orderBy, String direction, Pageable pageable);
}
