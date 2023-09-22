package com.adn.tokoku.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class StockException extends RuntimeException {

    public StockException(String message) {
        super(message);
    }

}
