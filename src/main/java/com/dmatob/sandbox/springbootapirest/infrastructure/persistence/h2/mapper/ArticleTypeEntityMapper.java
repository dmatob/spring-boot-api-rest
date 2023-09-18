package com.dmatob.sandbox.springbootapirest.infrastructure.persistence.h2.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.dmatob.sandbox.springbootapirest.domain.model.ArticleType;
import com.dmatob.sandbox.springbootapirest.infrastructure.persistence.h2.entity.ArticleTypeEntity;

@Mapper
public interface ArticleTypeEntityMapper {
 	
	public ArticleType toArticleType (ArticleTypeEntity articleEntity);
	public List<ArticleType> toListOfArticlesTypes (List<ArticleTypeEntity> listOfArticleEntities);
	public ArticleTypeEntity fromArticleType (ArticleType article);
	public List<ArticleTypeEntity> fromArticleTypeList (List<ArticleType> listOfArticles);

}
