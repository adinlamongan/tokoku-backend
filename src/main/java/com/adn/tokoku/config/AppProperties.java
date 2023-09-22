package com.adn.tokoku.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
//@ConfigurationProperties(prefix = "app.baseurl")
public class AppProperties {
    @Value("${app.baseurl.storage}")
    private String BASE_URL_STORAGE;

    private String productStorage = "product/";
    private String noImage = "img/noimage.png";

    public String getProductStorage() {
        return BASE_URL_STORAGE + productStorage;
    }
    public String getNoImage() {
        return BASE_URL_STORAGE + noImage;
    }

    
}
