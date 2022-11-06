package es.sprinter.technicaltest.domain.service;

import java.math.BigDecimal;
import java.util.List;

import es.sprinter.technicaltest.domain.Article;

public interface ArticleService {

	Article createArticle(Article article);
	
	List<Article> getAllArticles();

	Article getArticle(String code);

	Article updateArticle(String code, Article article);
	
	Article updatePriceArticleByCode(String code, BigDecimal price);

	void deleteArticleByCode(String code);
}
