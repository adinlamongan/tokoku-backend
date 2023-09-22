package com.adn.tokoku.service;

import com.adn.tokoku.dto.RegisterRequestDTO;

public interface AppUserService {
    void registerUser(RegisterRequestDTO dto);
}
