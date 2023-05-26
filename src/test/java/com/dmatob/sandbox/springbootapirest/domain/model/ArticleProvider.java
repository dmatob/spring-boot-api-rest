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
		
		return new Article(Math.abs(random.nextLong()), generatedCode, generatedDesc, BigDecimal.valueOf(random.nextDouble()), LocalDateTime.of(1960, 1, 1, 0, 0, 0 ));
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
		
		return new Article(null, generatedCode, generatedDesc, BigDecimal.valueOf(random.nextDouble()), null);
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
		
		return new Article(Math.abs(random.nextLong()), generatedCode, generatedDesc, BigDecimal.valueOf(random.nextDouble()), null);
	}
	
}
