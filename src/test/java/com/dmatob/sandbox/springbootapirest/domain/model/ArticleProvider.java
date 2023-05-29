package com.dmatob.sandbox.springbootapirest.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Random;

public class ArticleProvider {

	private static final int ASCII_VALUE_OF_A_LOWERCASE = 97;
	private static final int ASCII_VALUE_OF_Z_LOWERCASE = 122;

	public static Random random = new Random();

	public static Article getArticle() {
		String generatedCode = random.ints(ASCII_VALUE_OF_A_LOWERCASE, ASCII_VALUE_OF_Z_LOWERCASE + 1)
				.limit(20)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
				.toString();

		String generatedDesc = random.ints(ASCII_VALUE_OF_A_LOWERCASE, ASCII_VALUE_OF_Z_LOWERCASE + 1)
				.limit(80)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
				.toString();

		return Article.builder()
				.id(Math.abs(random.nextLong()))
				.code(generatedCode)
				.description(generatedDesc)
				.price(BigDecimal.valueOf(random.nextDouble()))
				.lastModificationDate(LocalDateTime.of(1960, 1, 1, 0, 0, 0))
				.build();

	}

	public static Article getArticleToInsert() {
		String generatedCode = random.ints(ASCII_VALUE_OF_A_LOWERCASE, ASCII_VALUE_OF_Z_LOWERCASE + 1)
				.limit(20)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
				.toString();

		String generatedDesc = random.ints(ASCII_VALUE_OF_A_LOWERCASE, ASCII_VALUE_OF_Z_LOWERCASE + 1)
				.limit(80)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
				.toString();

		return Article.builder()
				.code(generatedCode)
				.description(generatedDesc)
				.price(BigDecimal.valueOf(random.nextDouble()))
				.build();
	}

	public static Article getArticleToModify() {
		String generatedCode = random.ints(ASCII_VALUE_OF_A_LOWERCASE, ASCII_VALUE_OF_Z_LOWERCASE + 1)
				.limit(20)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
				.toString();

		String generatedDesc = random.ints(ASCII_VALUE_OF_A_LOWERCASE, ASCII_VALUE_OF_Z_LOWERCASE + 1)
				.limit(80)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
				.toString();

		return Article.builder()
				.id(Math.abs(random.nextLong()))
				.code(generatedCode)
				.description(generatedDesc)
				.price(BigDecimal.valueOf(random.nextDouble()))
				.build();
	}

}
