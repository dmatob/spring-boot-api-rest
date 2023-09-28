package com.dmatob.sandbox.springboot.infrastructure.api.soap.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.dmatob.sandbox.springboot.domain.model.ArticleType;
import com.dmatob.sandbox.springboot.infrastructure.api.soap.generated.ArticleTypeSoapDTO;

@Mapper
public interface ArticleTypeSoapMapper {
    
    public ArticleTypeSoapDTO toArticleTypeDTO (ArticleType domainModel);
	public List<ArticleTypeSoapDTO> toListOfArticleTypeDTOs (List<ArticleType> lstDomainModel);
	public ArticleType fromArticleTypeDTO (ArticleTypeSoapDTO dto);
	public List<ArticleType> fromListOfArticleTypeDTOs (List<ArticleTypeSoapDTO> lstDto);

}
