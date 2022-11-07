package es.sprinter.technicaltest.infrastructure.persistence.h2;

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

import es.sprinter.technicaltest.domain.Article;
import es.sprinter.technicaltest.domain.ArticleProvider;
import es.sprinter.technicaltest.domain.repository.ArticleRepository;

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
		assertEquals(lstArticles, lstOriginalArticles);
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
		assertEquals(insertedArticle, result.get());
	}
	
	@Test
	void shouldFindById_thenReturnEmpty() {
		final Optional<Article> result = articleRepository.findById(1l);
		assertTrue(result.isEmpty());
	}
	
	@Test
	void shouldFindByCode_thenReturnArticle() {
		Article article = ArticleProvider.getArticleToInsert();
		Article insertedArticle = articleRepository.save(article);
		
		final Optional<Article> result = articleRepository.findByCode(insertedArticle.getCode());
		assertEquals(insertedArticle, result.get());
	}
	
	@Test
	void shouldFindByCode_thenReturnEmpty() {
		final Optional<Article> result = articleRepository.findByCode("aabb");
		assertTrue(result.isEmpty());
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
