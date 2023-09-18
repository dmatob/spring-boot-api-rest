package com.dmatob.sandbox.springbootapirest.infrastructure.persistence.h2.repository.impl;

import java.util.List;
import java.util.Optional;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Repository;

import com.dmatob.sandbox.springbootapirest.domain.model.Article;
import com.dmatob.sandbox.springbootapirest.domain.repository.ArticleRepository;
import com.dmatob.sandbox.springbootapirest.infrastructure.persistence.h2.entity.ArticleEntity;
import com.dmatob.sandbox.springbootapirest.infrastructure.persistence.h2.mapper.ArticleEntityMapper;
import com.dmatob.sandbox.springbootapirest.infrastructure.persistence.h2.repository.jpa.ArticleRepositoryJPA;

@Repository
public class ArticleRepositoryImpl implements ArticleRepository {

	private final ArticleRepositoryJPA articleRepositoryJPA;
	private ArticleEntityMapper mapper = Mappers.getMapper(ArticleEntityMapper.class);

	public ArticleRepositoryImpl(ArticleRepositoryJPA articleJPARepository) {
		this.articleRepositoryJPA = articleJPARepository;
	}

	@Override
	public List<Article> findAll() {
		return this.mapper.toListOfArticles(this.articleRepositoryJPA.findAll());
	}

	@Override
	public Optional<Article> findById(Long id) {
		Optional<Article> article = Optional.empty();
		Optional<ArticleEntity> articleEntity = this.articleRepositoryJPA.findById(id);
		if (articleEntity.isPresent()) {
			article = Optional.of(this.mapper.toArticle(articleEntity.get()));
		}
		return article;
	}
	
	@Override
	public Optional<Article> findByCode(String code) {
		Optional<Article> article = Optional.empty();
		Optional<ArticleEntity> articleEntity = this.articleRepositoryJPA.findByCode(code);
		if (articleEntity.isPresent()) {
			article = Optional.of(this.mapper.toArticle(articleEntity.get()));
		}
		return article;
	}

	@Override
	public Article insert(Article data) {
		return save(this.mapper.fromArticle(data));
	}

	@Override
	public Article update(Long id, Article data) {
		ArticleEntity entity = this.mapper.fromArticle(data);
		entity.setId(id);
		return save(entity);
	}

	private Article save(ArticleEntity entity){
		return this.mapper.toArticle(this.articleRepositoryJPA.save(entity));
	}

	@Override
	public void delete(Article data){
		this.articleRepositoryJPA.delete(this.mapper.fromArticle(data));
	}

}
