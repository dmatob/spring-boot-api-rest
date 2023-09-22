package com.dmatob.sandbox.springboot.domain.model;

import java.util.Random;

public class BaseProvider {
    
    private static final int ASCII_VALUE_OF_A_LOWERCASE = 97;
	private static final int ASCII_VALUE_OF_Z_LOWERCASE = 122;

    public static Random random = new Random();

    public static String getRandomString(int size) {
		return random.ints(ASCII_VALUE_OF_A_LOWERCASE, ASCII_VALUE_OF_Z_LOWERCASE + 1)
				.limit(size)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
				.toString();
    }
}
