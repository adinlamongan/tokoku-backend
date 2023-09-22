package com.adn.tokoku.service;


import com.adn.tokoku.dto.ProductCreateRequest;
import com.adn.tokoku.dto.ProductDetailResponseDTO;
import com.adn.tokoku.dto.ResultPageResponseDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ProductService {
    public ResultPageResponseDTO findProductList(Integer page, Integer limit, String sortBy, String direction,String nama, String color, Number hargaAwal, Number hargaAkhir);

    void createProduct(ProductCreateRequest dto, MultipartFile[] multipartFile) throws IOException;

    ProductDetailResponseDTO findProductDetail(int id);

}
