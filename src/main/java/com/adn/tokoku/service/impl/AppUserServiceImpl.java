package com.adn.tokoku.service.impl;

import com.adn.tokoku.config.AppProperties;
import com.adn.tokoku.dto.RegisterRequestDTO;
import com.adn.tokoku.model.AppUser;
import com.adn.tokoku.repository.AppUserRepo;
import com.adn.tokoku.service.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AppUserServiceImpl implements AppUserService {


    private AppUserRepo appUserRepo;
    private PasswordEncoder passwordEncoder;
    private AppProperties appProperties;

    @Override
    public void registerUser(RegisterRequestDTO dto) {
        AppUser appUser = new AppUser();
        appUser.setEmail(dto.getEmail());
        appUser.setUsername(dto.getUsername());
        appUser.setPassword(passwordEncoder.encode(dto.getPassword()));
        appUserRepo.save(appUser);

    }
}
