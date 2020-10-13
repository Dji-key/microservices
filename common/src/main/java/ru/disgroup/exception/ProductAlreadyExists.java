package ru.disgroup.exception;

import java.text.MessageFormat;

public class ProductAlreadyExists extends RuntimeException {

    public ProductAlreadyExists(Long id) {
        super(MessageFormat.format("Продукт с id={0} уже существует", id));
    }
}
