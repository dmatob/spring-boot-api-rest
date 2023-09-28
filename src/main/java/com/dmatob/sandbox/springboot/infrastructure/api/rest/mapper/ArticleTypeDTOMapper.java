package com.dmatob.sandbox.springboot.infrastructure.api.rest.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.dmatob.sandbox.springboot.domain.model.ArticleType;
import com.dmatob.sandbox.springboot.infrastructure.api.rest.dto.ArticleTypeDTO;

@Mapper
public interface ArticleTypeDTOMapper {
    
    public ArticleTypeDTO toArticleTypeDTO (ArticleType domainModel);
	public List<ArticleTypeDTO> toListOfArticleTypeDTOs (List<ArticleType> lstDomainModel);
	public ArticleType fromArticleTypeDTO (ArticleTypeDTO dto);
	public List<ArticleType> fromListOfArticleTypeDTOs (List<ArticleTypeDTO> lstDto);

}
