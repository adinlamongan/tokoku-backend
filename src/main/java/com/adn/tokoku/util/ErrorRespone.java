package com.adn.tokoku.util;

import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ErrorRespone {
    private Date timestamp = new Date();
    private Integer status;
    private String error;
    private String path;
    private List<String> message;

}
