package es.sprinter.technicaltest.application.rest;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import es.sprinter.technicaltest.application.rest.dto.ArticleDTO;
import es.sprinter.technicaltest.application.rest.dto.ArticleModificationRequestDTO;
import es.sprinter.technicaltest.domain.Article;

@Component
public class ArticleDTOMapper {

	private ModelMapper mapper = new ModelMapper();
	
	public ArticleDTO toArticleDTO (Article article) {
		return article != null ? this.mapper.map(article, ArticleDTO.class) : null;
	}
	
	public List<ArticleDTO> toListOfArticleDTOs (List<Article> listOfArticles) {
		return listOfArticles != null ? listOfArticles.stream().map(this::toArticleDTO).collect(Collectors.toList()) : null;
	}
	
	public Article fromArticleDTO (ArticleModificationRequestDTO articleDTO) {
		Article article = null;
		if (articleDTO != null) {
			article = new Article(null, articleDTO.getCode(), articleDTO.getDescription(), articleDTO.getPrice(), null);
		}
		return article;
	}
		
}
