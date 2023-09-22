package com.adn.tokoku.repository;

import com.adn.tokoku.model.ProductReview;
import com.adn.tokoku.model.ProductSize;
import org.springframework.data.repository.CrudRepository;

public interface ProductReviewRepo extends CrudRepository<ProductReview, Integer> {
}
