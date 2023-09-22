package com.adn.tokoku.service.impl;

import com.adn.tokoku.config.AppProperties;
import com.adn.tokoku.dto.ProductCreateRequest;
import com.adn.tokoku.dto.ProductDetailResponseDTO;
import com.adn.tokoku.dto.ProductListResponseDTO;
import com.adn.tokoku.dto.ResultPageResponseDTO;
import com.adn.tokoku.exception.ResourceNotFoundException;
import com.adn.tokoku.model.ProductColor;
import com.adn.tokoku.model.ProductImage;
import com.adn.tokoku.model.ProductSize;
import com.adn.tokoku.model.Products;
import com.adn.tokoku.repository.*;
import com.adn.tokoku.service.ProductService;
import com.adn.tokoku.util.FileUploadUtil;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private ProductRepo productRepo;
    private ProductQueryRepo productQueryRepo;
    private ProductImageRepo productImageRepo;
    private ProductSizeRepo productSizeRepo;
    private ProductColorRepo productColorRepo;
    private AppProperties appProperties;

    @Override
    public ResultPageResponseDTO findProductList(Integer page, Integer limit, String orderBy, String direction, String nama, String color, Number hargaAwal, Number hargaAkhir) {
        Pageable pageable = PageRequest.of(page-1,limit);
        Page<ProductListResponseDTO> data = productQueryRepo.findProducts(nama, color, hargaAwal, hargaAkhir, orderBy, direction, pageable);
        ResultPageResponseDTO resultPageResponseDTO = new ResultPageResponseDTO();
        resultPageResponseDTO.setResult(data.getContent());
        resultPageResponseDTO.setTotalPages(data.getTotalPages());
        resultPageResponseDTO.setPageSize(data.getSize());
        resultPageResponseDTO.setTotalRecord((int) data.getTotalElements());
        resultPageResponseDTO.setRecordOfElement(data.getNumberOfElements());
        resultPageResponseDTO.setCurrentPage(page);
        return resultPageResponseDTO;
    }


    @Override
    @Transactional
    public void createProduct(ProductCreateRequest dto, MultipartFile[] multipartFile) throws IOException {
        Products product = new Products();
        product.setNama(dto.getNama());
        product.setDeskripsi(dto.getDeskripsi());
        product.setMen(dto.getMen());
        product.setWomen(dto.getWomen());
        product.setKategoriId(dto.getKategori_id());
        product.setMaterials(dto.getMaterials());
        product.setPanjang(dto.getPanjang());
        product.setLebar(dto.getLebar());
        product.setPl(dto.getPl());
        product.setHarga(dto.getHarga());
        productRepo.save(product);
        int length = dto.getSize().size();
        int i = 0;
        while (i < length) {
            ProductSize productSize = new ProductSize();
            productSize.setProductId(product.getId());
            productSize.setSize(dto.getSize().get(i));
            productSizeRepo.save(productSize);
            i++;
        }

        i = 0;
        length = dto.getColor().size();
        while (i < length) {
            ProductColor productColor = new ProductColor();
            productColor.setProductId(product.getId());
            productColor.setColor(dto.getColor().get(i));
            productColorRepo.save(productColor);
            i++;
        }

        i = 0;
        String fileNameImage;
        String uploadDir = "storage/product/";
        length = multipartFile.length;
        while (i < length) {
            fileNameImage = StringUtils.cleanPath(Objects.requireNonNull(multipartFile[i].getOriginalFilename()));
            ProductImage productImage = new ProductImage();
            productImage.setProductId(product.getId());
            productImage.setImage(UUID.randomUUID().toString() + getFileExtension(fileNameImage));
            if (i == 0) {
                productImage.setIsPrimary(true);
            }
            productImageRepo.save(productImage);
            FileUploadUtil.saveFile(uploadDir, productImage.getImage(), multipartFile[i]);
            i++;
        }


    }



    private String getFileExtension(String filename) {
        int i = filename.lastIndexOf('.');
        return "." + filename.substring(i + 1);
    }


    @Override
    public ProductDetailResponseDTO findProductDetail(int id) {

        Products product = productRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        ProductDetailResponseDTO dto = new ProductDetailResponseDTO();
        dto.setId(product.getId());
        dto.setNama(product.getNama());
        dto.setDeskripsi(product.getDeskripsi());
        dto.setMen(product.getMen());
        dto.setWomen(product.getWomen());
        dto.setKategoriId(product.getKategoriId());
        dto.setWeight(product.getWeight());
        dto.setPanjang(product.getPanjang());
        dto.setLebar(product.getLebar());
        dto.setPl(product.getPl());
        dto.setMaterials(product.getMaterials());
        dto.setHarga(product.getHarga());
        List<ProductImage> imageList = productImageRepo.findByProductId(id).stream().map((e)-> {
            ProductImage productImage = new ProductImage();
            productImage.setProductId(e.getProductId());
            productImage.setId(e.getId());
            productImage.setImage(appProperties.getProductStorage() + e.getImage());
            return  productImage;
        }).collect(Collectors.toList());
        dto.setImages(imageList);
        dto.setColors(productColorRepo.findByProductId(id));
        dto.setSizes(productSizeRepo.findByProductId(id));
        return dto;
    }
}
