package ru.disgroup.exception;

import java.text.MessageFormat;

public class ArticleAlreadyExists extends RuntimeException {

    public ArticleAlreadyExists(Long id) {
        super(MessageFormat.format("Статья с id={0} уже существует", id));
    }
}
