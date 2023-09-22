package com.adn.tokoku.controller;

import com.adn.tokoku.dto.CartQueryResponseDTO;
import com.adn.tokoku.dto.CartRequestDTO;
import com.adn.tokoku.service.CartService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class CartController {

    private CartService cartService;

    @PostMapping("/cart")
    public ResponseEntity<Void> createUpdate(@Valid @RequestBody CartRequestDTO dto) {
        cartService.createUpdateCart(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }



    @GetMapping("/cart")
    public ResponseEntity<Object> getListCart() {
        List<CartQueryResponseDTO> response = cartService.findCart();
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    @GetMapping("/cart/qty")
    public ResponseEntity<Object> getTotalQty() {
        Map<String, Object> responseBody = new LinkedHashMap<>();
        responseBody.put("qty", cartService.getQty() );
        return ResponseEntity.ok(responseBody);
    }
}
