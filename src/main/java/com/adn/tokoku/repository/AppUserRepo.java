package com.adn.tokoku.repository;

import com.adn.tokoku.model.AppUser;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AppUserRepo extends CrudRepository<AppUser,Integer> {

    Optional<AppUser> findByUsername(String username);
    Optional<AppUser> findByEmail(String email);
}
