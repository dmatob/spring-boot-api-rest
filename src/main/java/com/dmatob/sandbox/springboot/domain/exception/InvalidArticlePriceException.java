package com.dmatob.sandbox.springboot.domain.exception;

public class InvalidArticlePriceException extends RuntimeException {

	private static final long serialVersionUID = 8241428589532194515L;

	public InvalidArticlePriceException(final String message) {
        super(message);
    }
}
