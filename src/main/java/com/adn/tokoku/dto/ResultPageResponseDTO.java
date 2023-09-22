package com.adn.tokoku.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;


@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ResultPageResponseDTO implements Serializable{

    private List<?> result;
    private int currentPage;
    private int pageSize;
    private int totalRecord;
    private int recordOfElement;
    private int totalPages;

}
