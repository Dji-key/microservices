package ru.disgroup.exception;

import java.text.MessageFormat;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(Long id) {
        super(MessageFormat.format("Продукт с id={0} не найден", id));
    }
}
