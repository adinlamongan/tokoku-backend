package com.adn.tokoku.service.impl;

import com.adn.tokoku.config.AppProperties;
import com.adn.tokoku.dto.CartQueryResponseDTO;
import com.adn.tokoku.dto.CartRequestDTO;
import com.adn.tokoku.exception.ResourceNotFoundException;
import com.adn.tokoku.model.Cart;
import com.adn.tokoku.model.ProductStok;
import com.adn.tokoku.repository.CartRepo;
import com.adn.tokoku.repository.ProductStokRepo;
import com.adn.tokoku.service.CartService;
import com.adn.tokoku.util.UserAktif;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CartServiceImpl implements CartService {

    private CartRepo cartRepo;
    private UserAktif userAktif;
    private ProductStokRepo productStokRepo;
    private AppProperties appProperties;

    @Override
    public void createUpdateCart(CartRequestDTO dto) {
        if (dto.getStokId() > 0) {
            Cart cart = cartRepo.findByUserIdAndStokId(userAktif.getUserId(), dto.getStokId());
            ProductStok productStok = productStokRepo.findById(dto.getStokId()).orElseThrow(() -> new ResourceNotFoundException("product tidak ditemukan"));
            if (dto.getQty() > productStok.getStock()) {
                throw new RuntimeException("Stock tidak cukup");
            }else {
                if (cart == null) {
                    Cart data = new Cart();
                    data.setStokId(dto.getStokId());
                    data.setQty(dto.getQty());
                    data.setUserId(userAktif.getUserId());
                    cartRepo.save(data);
                } else {
                    cart.setQty(dto.getQty());
                    cartRepo.save(cart);
                }
            }
        } else {
            ProductStok productStok = productStokRepo.findByProductIdAndSizeAndColor(dto.getProductId(), dto.getSize(), dto.getColor()).orElseThrow(() -> new ResourceNotFoundException("product tidak ditemukan"));
            if (dto.getQty() < productStok.getStock()) {
                Cart cart = cartRepo.findByUserIdAndStokId(userAktif.getUserId(), productStok.getId());
                if (cart == null) {
                    Cart data = new Cart();
                    data.setStokId(productStok.getId());
                    data.setQty(dto.getQty());
                    data.setUserId(userAktif.getUserId());
                    cartRepo.save(data);
                } else {
                    cart.setQty(dto.getQty());
                    cartRepo.save(cart);
                }
            } else {
                throw new RuntimeException("Stock tidak cukup");
            }
        }

    }

    @Override
    public Number getQty() {
        return cartRepo.getTotalQty(userAktif.getUserId());
    }

    @Override
    public List<CartQueryResponseDTO> findCart() {
        return cartRepo.queryFindByUserId(userAktif.getUserId()).stream().map((e) -> {
            CartQueryResponseDTO dto = new CartQueryResponseDTO();
            dto.setId(e.getId());
            dto.setNama(e.getNama());
            dto.setColor(e.getColor());
            dto.setSize(e.getSize());
            dto.setQty(e.getQty());
            dto.setHarga(e.getHarga());
            dto.setTotal(e.getTotal());
            dto.setImage(appProperties.getProductStorage() + e.getImage());
            dto.setProductId(e.getProductId());
            dto.setStock(e.getStock());
            return dto;
        }).collect(Collectors.toList());
    }
}
