package com.dmatob.sandbox.springboot.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.dmatob.sandbox.springboot.domain.model.Article;
import com.dmatob.sandbox.springboot.domain.model.ArticleType;

public class ArticleProvider extends BaseProvider {

	public static Article getArticle() {
		return getArticle(null);

	}

	public static Article getArticle(Long id) {
		return Article.builder()
				.id(id != null ? id : Math.abs(random.nextLong()))
				.code(getRandomString(20))
				.description(getRandomString(80))
				.price(BigDecimal.valueOf(random.nextDouble()))
				.type(getArticleType())
				.lastModificationDate(LocalDateTime.of(1960, 1, 1, 0, 0, 0))
				.build();

	}

	public static Article getArticleToInsert() {
		return Article.builder()
				.code(getRandomString(20))
				.description(getRandomString(80))
				.price(BigDecimal.valueOf(random.nextDouble()))
				.type(getArticleType())
				.build();
	}

	public static Article getArticleToModify() {
		return Article.builder()
				.id(Math.abs(random.nextLong()))
				.code(getRandomString(20))
				.description(getRandomString(80))
				.price(BigDecimal.valueOf(random.nextDouble()))
				.type(getArticleType())
				.build();
	}

	public static ArticleType getArticleType() {
		return ArticleType.builder()
					.id(Math.abs(random.nextLong()))
					.code(getRandomString(20))
					.description(getRandomString(80))
					.build();
	}

}
