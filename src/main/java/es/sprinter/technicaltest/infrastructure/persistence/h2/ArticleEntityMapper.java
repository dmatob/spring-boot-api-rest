package es.sprinter.technicaltest.infrastructure.persistence.h2;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import es.sprinter.technicaltest.domain.Article;

@Component
public class ArticleEntityMapper {

	private ModelMapper mapper = new ModelMapper();
	
	public Article toArticle (ArticleEntity articleEntity) {
		Article article = null;
		if (articleEntity != null) {
			article = new Article(articleEntity.getId(), articleEntity.getCode(), articleEntity.getDescription(), articleEntity.getPrice(), articleEntity.getLastModificationDate());
		}
		return article;
	}
	
	public List<Article> toListOfArticles (List<ArticleEntity> listOfArticleEntities) {
		return listOfArticleEntities != null ? listOfArticleEntities.stream().map(this::toArticle).collect(Collectors.toList()) : null;
	}
	
	public ArticleEntity fromArticle (Article article) {
		return article != null ? this.mapper.map(article, ArticleEntity.class) : null;
	}
	
	public List<ArticleEntity> fromArticleList (List<Article> listOfArticles) {
		return listOfArticles != null ? listOfArticles.stream().map(this::fromArticle).collect(Collectors.toList()) : null;
	}
	
}
