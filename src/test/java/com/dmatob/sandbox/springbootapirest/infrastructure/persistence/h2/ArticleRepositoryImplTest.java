package com.dmatob.sandbox.springbootapirest.infrastructure.persistence.h2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.dmatob.sandbox.springbootapirest.domain.model.Article;
import com.dmatob.sandbox.springbootapirest.domain.model.ArticleProvider;
import com.dmatob.sandbox.springbootapirest.domain.repository.ArticleRepository;

@SpringJUnitConfig
@SpringBootTest
@TestPropertySource("classpath:application-persistence.properties")
class ArticleRepositoryImplTest {

	@Autowired
	private ArticleRepositoryJPA articleRepositoryJPA;

	@Autowired
	private ArticleRepository articleRepository;

	@AfterEach
	void cleanUp() {
		articleRepositoryJPA.deleteAll();
	}
	
	@Test
	void shouldFindAll_thenReturnArticles() {
		Article article0 = ArticleProvider.getArticleToInsert();
		Article insertedArticle0 = articleRepository.save(article0);
		
		Article article1 = ArticleProvider.getArticleToInsert();
		Article insertedArticle1 = articleRepository.save(article1);
		
		Article article2 = ArticleProvider.getArticleToInsert();
		Article insertedArticle2 = articleRepository.save(article2);
		
		List<Article> lstOriginalArticles = Arrays.asList(insertedArticle0, insertedArticle1, insertedArticle2);
		
		List<Article> lstArticles = this.articleRepository.findAll();
		
		assertNotNull(lstArticles);
		assertEquals(lstArticles.size(), lstOriginalArticles.size());
		assertEquals(lstArticles.get(0).getCode(), lstOriginalArticles.get(0).getCode());
		assertEquals(lstArticles.get(0).getDescription(), lstOriginalArticles.get(0).getDescription());
		assertEquals(lstArticles.get(0).getPrice(), lstOriginalArticles.get(0).getPrice());
		assertEquals(lstArticles.get(0).getId(), lstOriginalArticles.get(0).getId());

		assertEquals(lstArticles.get(1).getCode(), lstOriginalArticles.get(1).getCode());
		assertEquals(lstArticles.get(1).getDescription(), lstOriginalArticles.get(1).getDescription());
		assertEquals(lstArticles.get(1).getPrice(), lstOriginalArticles.get(1).getPrice());
		assertEquals(lstArticles.get(1).getId(), lstOriginalArticles.get(1).getId());
		
		assertEquals(lstArticles.get(2).getCode(), lstOriginalArticles.get(2).getCode());
		assertEquals(lstArticles.get(2).getDescription(), lstOriginalArticles.get(2).getDescription());
		assertEquals(lstArticles.get(2).getPrice(), lstOriginalArticles.get(2).getPrice());
		assertEquals(lstArticles.get(2).getId(), lstOriginalArticles.get(2).getId());
	}

	@Test
	void shouldSave_thenReturnArticle() {
		Article article = ArticleProvider.getArticleToInsert();
		Article insertedArticle = articleRepository.save(article);
		
		assertNotNull(insertedArticle);
		assertNotNull(insertedArticle.getId());
		assertEquals(article.getCode(), insertedArticle.getCode());
		assertEquals(article.getDescription(), insertedArticle.getDescription());
		assertEquals(article.getPrice(), insertedArticle.getPrice());
	}
	
	@Test
	void shouldFindById_thenReturnArticle() {
		Article article = ArticleProvider.getArticleToInsert();
		Article insertedArticle = articleRepository.save(article);
		
		final Optional<Article> result = articleRepository.findById(insertedArticle.getId());
		
		assertEquals(insertedArticle.getCode(), result.get().getCode());
		assertEquals(insertedArticle.getDescription(), result.get().getDescription());
		assertEquals(insertedArticle.getPrice(), result.get().getPrice());
		assertEquals(insertedArticle.getId(), result.get().getId());
		assertEquals(insertedArticle.getLastModificationDate(), result.get().getLastModificationDate());
	}
	
	@Test
	void shouldFindById_thenReturnEmpty() {
		final Optional<Article> result = articleRepository.findById(1l);
		assertTrue(!result.isPresent());
	}
	
	@Test
	void shouldFindByCode_thenReturnArticle() {
		Article article = ArticleProvider.getArticleToInsert();
		Article insertedArticle = articleRepository.save(article);
		
		final Optional<Article> result = articleRepository.findByCode(insertedArticle.getCode());
		
		assertEquals(insertedArticle.getCode(), result.get().getCode());
		assertEquals(insertedArticle.getDescription(), result.get().getDescription());
		assertEquals(insertedArticle.getPrice(), result.get().getPrice());
		assertEquals(insertedArticle.getId(), result.get().getId());
		assertEquals(insertedArticle.getLastModificationDate(), result.get().getLastModificationDate());
	}
	
	@Test
	void shouldFindByCode_thenReturnEmpty() {
		final Optional<Article> result = articleRepository.findByCode("aabb");
		assertTrue(!result.isPresent());
	}
	
	@Test
	void shouldDelete_thenDeleteIt() {
		Article article0 = ArticleProvider.getArticleToInsert();
		Article insertedArticle0 = articleRepository.save(article0);
		
		Article article1 = ArticleProvider.getArticleToInsert();
		Article insertedArticle1 = articleRepository.save(article1);
		
		articleRepository.delete(insertedArticle0);
		
		final List<Article> result = articleRepository.findAll();
		
		assertNotNull(result);
		assertEquals(1, result.size());
		assertEquals(insertedArticle1.getId(), result.get(0).getId());
	}
	
	

}
