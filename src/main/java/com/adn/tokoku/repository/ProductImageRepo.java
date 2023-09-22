package com.adn.tokoku.repository;

import com.adn.tokoku.model.ProductImage;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductImageRepo extends CrudRepository<ProductImage, Integer> {

    List<ProductImage> findByProductId(int productId);
}
