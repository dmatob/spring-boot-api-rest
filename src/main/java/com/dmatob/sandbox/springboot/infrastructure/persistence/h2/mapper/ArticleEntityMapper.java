package com.dmatob.sandbox.springboot.infrastructure.persistence.h2.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.dmatob.sandbox.springboot.domain.model.Article;
import com.dmatob.sandbox.springboot.infrastructure.persistence.h2.entity.ArticleEntity;

@Mapper(uses = ArticleTypeEntityMapper.class)
public interface ArticleEntityMapper {

	public Article toArticle (ArticleEntity articleEntity);

	public List<Article> toListOfArticles (List<ArticleEntity> listOfArticleEntities);

	@Mapping(source = "type.id", target = "typeId")
	public ArticleEntity fromArticle (Article article);

	@Mapping(source = "type.id", target = "typeId")
	public List<ArticleEntity> fromArticleList (List<Article> listOfArticles);
	
}
