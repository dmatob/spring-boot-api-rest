package com.dmatob.sandbox.springboot.application.exception;

public class ArticleTypeNotFoundException extends RuntimeException {

	public ArticleTypeNotFoundException(final String message) {
        super(message);
    }
}
