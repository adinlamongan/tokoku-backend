package com.adn.tokoku.repository;

import com.adn.tokoku.model.ProductColor;
import com.adn.tokoku.model.ProductImage;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductColorRepo extends CrudRepository<ProductColor, Integer> {
    List<ProductColor> findByProductId(int id);
}
