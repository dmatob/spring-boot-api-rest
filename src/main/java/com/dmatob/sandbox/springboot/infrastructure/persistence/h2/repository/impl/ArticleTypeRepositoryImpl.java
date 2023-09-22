package com.dmatob.sandbox.springboot.infrastructure.persistence.h2.repository.impl;

import java.util.Optional;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Repository;

import com.dmatob.sandbox.springboot.domain.model.ArticleType;
import com.dmatob.sandbox.springboot.domain.repository.ArticleTypeRepository;
import com.dmatob.sandbox.springboot.infrastructure.persistence.h2.entity.ArticleTypeEntity;
import com.dmatob.sandbox.springboot.infrastructure.persistence.h2.mapper.ArticleTypeEntityMapper;
import com.dmatob.sandbox.springboot.infrastructure.persistence.h2.repository.jpa.ArticleTypeRepositoryJPA;

@Repository
public class ArticleTypeRepositoryImpl implements ArticleTypeRepository {

    private final ArticleTypeRepositoryJPA articleTypeRepositoryJPA;
	private ArticleTypeEntityMapper mapper = Mappers.getMapper(ArticleTypeEntityMapper.class);


    public ArticleTypeRepositoryImpl(ArticleTypeRepositoryJPA articleTypeRepositoryJPA) {
		this.articleTypeRepositoryJPA = articleTypeRepositoryJPA;
	}

    @Override
    public Optional<ArticleType> findById(Long id) {
        Optional<ArticleType> articleType = Optional.empty();
		Optional<ArticleTypeEntity> articleTypeEntity = this.articleTypeRepositoryJPA.findById(id);
		if (articleTypeEntity.isPresent()) {
			articleType = Optional.of(this.mapper.toArticleType(articleTypeEntity.get()));
		}
		return articleType;
    }
    
}
