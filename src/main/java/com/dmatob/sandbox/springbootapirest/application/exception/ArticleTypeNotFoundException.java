package com.dmatob.sandbox.springbootapirest.application.exception;

public class ArticleTypeNotFoundException extends RuntimeException {

	public ArticleTypeNotFoundException(final String message) {
        super(message);
    }
}
