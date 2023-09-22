package com.adn.tokoku.controller;

import com.adn.tokoku.dto.ProductCreateRequest;
import com.adn.tokoku.dto.ProductDetailResponseDTO;
import com.adn.tokoku.dto.ProductListResponseDTO;
import com.adn.tokoku.dto.ResultPageResponseDTO;
import com.adn.tokoku.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/product")
    public ResponseEntity<ResultPageResponseDTO> findProductList(
            @RequestParam(name = "pages", required = true, defaultValue = "1") Integer pages,
            @RequestParam(name = "limit", required = true, defaultValue = "10") Integer limit,
            @RequestParam(name = "sortBy", required = true, defaultValue = "nama") String sortBy,
            @RequestParam(name = "direction", required = true, defaultValue = "asc") String direction,
            @RequestParam(name = "nama", required = false, defaultValue = "") String nama,
            @RequestParam(name = "harga_awal", required = false, defaultValue = "0") Number hargaAwal,
            @RequestParam(name = "harga_akhir", required = false, defaultValue = "0") Number hargaAkhir,
            @RequestParam(name = "warna", required = false, defaultValue = "") String warna) {
        ResultPageResponseDTO response = productService.findProductList(pages, limit, sortBy, direction, nama, warna,
                hargaAwal, hargaAkhir);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Object> productDetail(@PathVariable("id") int id) {
        ProductDetailResponseDTO response = productService.findProductDetail(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/product")
    public ResponseEntity<Void> create(@Valid  @RequestBody @RequestParam("image") MultipartFile[] multipartFile, ProductCreateRequest dto) throws IOException {
        productService.createProduct(dto, multipartFile);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
