package com.adn.tokoku.repository;

import com.adn.tokoku.model.ProductStok;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProductStokRepo extends CrudRepository<ProductStok, Integer> {
    Optional<ProductStok> findByProductIdAndSizeAndColor(int productId, String size, String color);

}
