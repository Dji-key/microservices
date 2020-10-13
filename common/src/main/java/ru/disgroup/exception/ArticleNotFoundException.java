package ru.disgroup.exception;

import java.text.MessageFormat;

public class ArticleNotFoundException extends RuntimeException {

    public ArticleNotFoundException(String message) {
        super(message);
    }

    public ArticleNotFoundException(Long id) {
        super(MessageFormat.format("Статья с id={0} не найдена", id));
    }
}
