package es.sprinter.technicaltest.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Random;

public class ArticleProvider {

	public static final int ASCII_VALUE_OF_A_LOWERCASE = 97;
	public static final int ASCII_VALUE_OF_Z_LOWERCASE = 122;
	
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
		
		return new Article(random.nextLong(), generatedCode, generatedDesc, BigDecimal.valueOf(random.nextDouble()), LocalDateTime.of(1960, 1, 1, 0, 0, 0 ));
	}
	
}
