package com.dmatob.sandbox.springbootapirest.infrastructure.api;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import com.dmatob.sandbox.springbootapirest.domain.model.Article;
import com.dmatob.sandbox.springbootapirest.infrastructure.api.dto.ArticleDTO;
import com.dmatob.sandbox.springbootapirest.infrastructure.api.dto.ArticleModificationRequestDTO;
	
public class ArticleDTOMapper {
	
	private ArticleDTOMapper() {}

	private static ModelMapper mapper = new ModelMapper();
	
	public static ArticleDTO toArticleDTO (Article article) {
		return article != null ? mapper.map(article, ArticleDTO.class) : null;
	}
	
	public static List<ArticleDTO> toListOfArticleDTOs (List<Article> listOfArticles) {
		return listOfArticles != null ? listOfArticles.stream().map(ArticleDTOMapper::toArticleDTO).collect(Collectors.toList()) : null;
	}
	
	public static Article fromArticleDTO (ArticleModificationRequestDTO articleDTO) {
		Article article = null;
		if (articleDTO != null) {
			article = Article
						.builder()
						.code(articleDTO.getCode())
						.description(articleDTO.getDescription())
						.price(articleDTO.getPrice())
						.build();
		}
		return article;
	}
		
}
