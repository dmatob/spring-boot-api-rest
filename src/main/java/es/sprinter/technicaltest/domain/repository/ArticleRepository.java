package es.sprinter.technicaltest.domain.repository;

import java.util.List;
import java.util.Optional;

import es.sprinter.technicaltest.domain.Article;

public interface ArticleRepository {
	
	List<Article> findAll();
	
	Optional<Article> findById(Long id);
	
	Optional<Article> findByCode(String code);
	
	Article save(Article article);
	
	void delete(Article article);
}