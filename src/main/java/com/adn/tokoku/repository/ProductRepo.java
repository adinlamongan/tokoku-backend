package com.adn.tokoku.repository;

import com.adn.tokoku.model.Products;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepo extends CrudRepository<Products, Integer> {



}
