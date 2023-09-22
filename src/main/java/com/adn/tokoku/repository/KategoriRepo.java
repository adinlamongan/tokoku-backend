package com.adn.tokoku.repository;

import com.adn.tokoku.model.Kategori;
import com.adn.tokoku.model.ProductStok;
import org.springframework.data.repository.CrudRepository;

public interface KategoriRepo extends CrudRepository<Kategori, Integer> {
}
