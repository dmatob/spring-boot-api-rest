package com.dmatob.sandbox.springbootapirest.infrastructure.persistence.h2;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.dmatob.sandbox.springbootapirest.domain.Article;
import com.dmatob.sandbox.springbootapirest.domain.repository.ArticleRepository;

@Repository
public class ArticleRepositoryImpl implements ArticleRepository {

	private final ArticleRepositoryJPA articleRepositoryJPA;

	private ArticleEntityMapper articleEntityMapper;

	public ArticleRepositoryImpl(ArticleRepositoryJPA articleJPARepository, ArticleEntityMapper articleEntityMapper) {
		this.articleRepositoryJPA = articleJPARepository;
		this.articleEntityMapper = articleEntityMapper;
	}

	@Override
	public List<Article> findAll() {
		return this.articleEntityMapper.toListOfArticles(this.articleRepositoryJPA.findAll());
	}

	@Override
	public Optional<Article> findById(Long id) {
		Optional<Article> article = Optional.empty();
		Optional<ArticleEntity> articleEntity = this.articleRepositoryJPA.findById(id);
		if (articleEntity.isPresent()) {
			article = Optional.of(this.articleEntityMapper.toArticle(articleEntity.get()));
		}
		return article;
	}
	
	@Override
	public Optional<Article> findByCode(String code) {
		Optional<Article> article = Optional.empty();
		Optional<ArticleEntity> articleEntity = this.articleRepositoryJPA.findByCode(code);
		if (articleEntity.isPresent()) {
			article = Optional.of(this.articleEntityMapper.toArticle(articleEntity.get()));
		}
		return article;
	}

	@Override
	public Article save(Article article) {
		return this.articleEntityMapper.toArticle(this.articleRepositoryJPA.save(this.articleEntityMapper.fromArticle(article)));
	}

	@Override
	public void delete(Article article) {
		this.articleRepositoryJPA.delete(this.articleEntityMapper.fromArticle(article));
	}

}
