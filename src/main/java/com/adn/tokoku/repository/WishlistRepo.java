package com.adn.tokoku.repository;

import com.adn.tokoku.model.Products;
import com.adn.tokoku.model.Wishlist;
import org.springframework.data.repository.CrudRepository;

public interface WishlistRepo extends CrudRepository<Wishlist, Integer> {


}
