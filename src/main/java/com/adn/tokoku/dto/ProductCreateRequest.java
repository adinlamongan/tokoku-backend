package com.adn.tokoku.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
public class ProductCreateRequest {
    @NotEmpty
    private String nama;
    @NotEmpty
    private String deskripsi;
    private Boolean men;
    private Boolean women;

    private int kategori_id;

    private double weight;
    private double panjang;
    private double lebar;
    private double pl;
    @NotNull
    private double harga;
    @NotEmpty
    private String materials;
    private List<String> size;
    private List<String> color;
    //private MultipartFile[] image;
}
