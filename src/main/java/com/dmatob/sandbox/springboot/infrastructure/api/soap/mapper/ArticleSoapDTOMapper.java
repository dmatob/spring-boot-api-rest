package com.dmatob.sandbox.springboot.infrastructure.api.soap.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.dmatob.sandbox.springboot.domain.model.Article;
import com.dmatob.sandbox.springboot.infrastructure.api.soap.generated.ArticleSoapDTO;

@Mapper
public interface ArticleSoapDTOMapper {
    
	public ArticleSoapDTO toArticleDTO (Article article);
	public List<ArticleSoapDTO> map (List<Article> lstOfArticles);
	public Article fromArticleDTO (ArticleSoapDTO articleDTO);
	public List<Article> fromListOfArticleDTOs (List<ArticleSoapDTO> listOfArticleDTOs);
}
