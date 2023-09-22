package com.adn.tokoku.repository;

import com.adn.tokoku.model.ProductColor;
import com.adn.tokoku.model.ProductSize;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductSizeRepo extends CrudRepository<ProductSize, Integer> {
    List<ProductSize> findByProductId(int id);
}
