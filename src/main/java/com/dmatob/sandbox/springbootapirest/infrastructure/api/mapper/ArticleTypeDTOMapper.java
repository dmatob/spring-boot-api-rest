package com.dmatob.sandbox.springbootapirest.infrastructure.api.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.dmatob.sandbox.springbootapirest.domain.model.ArticleType;
import com.dmatob.sandbox.springbootapirest.infrastructure.api.dto.ArticleTypeDTO;

@Mapper
public interface ArticleTypeDTOMapper {
    
    public ArticleTypeDTO toArticleTypeDTO (ArticleType domainModel);
	public List<ArticleTypeDTO> toListOfArticleTypeDTOs (List<ArticleType> lstDomainModel);
	public ArticleType fromArticleTypeDTO (ArticleTypeDTO dto);
	public List<ArticleType> fromListOfArticleTypeDTOs (List<ArticleTypeDTO> lstDto);

}
