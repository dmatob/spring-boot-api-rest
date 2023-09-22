package com.dmatob.sandbox.springboot.infrastructure.persistence.h2;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.dmatob.sandbox.springboot.domain.model.Article;
import com.dmatob.sandbox.springboot.domain.model.ArticleProvider;
import com.dmatob.sandbox.springboot.domain.repository.ArticleRepository;
import com.dmatob.sandbox.springboot.infrastructure.persistence.h2.entity.ArticleEntity;
import com.dmatob.sandbox.springboot.infrastructure.persistence.h2.mapper.ArticleEntityMapper;
import com.dmatob.sandbox.springboot.infrastructure.persistence.h2.repository.impl.ArticleRepositoryImpl;
import com.dmatob.sandbox.springboot.infrastructure.persistence.h2.repository.jpa.ArticleRepositoryJPA;

@SpringJUnitConfig
@SpringBootTest
@TestPropertySource("classpath:application-persistence.properties")
class ArticleRepositoryImplTest {

	private ArticleEntityMapper mapper = Mappers.getMapper(ArticleEntityMapper.class);
	private ArticleRepositoryJPA articleRepositoryJPA;
	private ArticleRepository articleRepository;
    
	@BeforeEach
    void setUp() {
		articleRepositoryJPA = Mockito.mock(ArticleRepositoryJPA.class);
        articleRepository = new ArticleRepositoryImpl(articleRepositoryJPA);
    }
	
	@Test
	void shouldFindAll_thenReturnArticles() {
		Article article0 = ArticleProvider.getArticleToInsert();
		Article article1 = ArticleProvider.getArticleToInsert();		
		Article article2 = ArticleProvider.getArticleToInsert();
		
		List<Article> lstOriginalArticles = Arrays.asList(article0, article1, article2);
		
		Mockito.when(articleRepositoryJPA.findAll()).thenReturn(this.mapper.fromArticleList(lstOriginalArticles));

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
	void shouldInsert_thenReturnArticle() {
		Article article = ArticleProvider.getArticleToInsert();
		assertAll(() -> articleRepository.insert(article));
	}

	@Test
	void shouldUpdate_thenReturnArticle() {
		Long id = 1l;
		Article article = ArticleProvider.getArticle(id);
		ArticleEntity articleEntity = this.mapper.fromArticle(article);
		articleEntity.setId(id);
		Mockito.when(articleRepositoryJPA.save(ArgumentMatchers.any(ArticleEntity.class))).thenReturn(articleEntity);
		Article articleResult = articleRepository.update(id, article);
		
		assertEquals(articleResult.getId(), id);
		assertEquals(articleResult.getCode(),article.getCode());
		assertEquals(articleResult.getDescription(),article.getDescription());
		assertEquals(articleResult.getPrice(),article.getPrice());
		assertEquals(articleResult.getType(),article.getType());
	}
	
	@Test
	void shouldFindById_thenReturnArticle() {
		Article article = ArticleProvider.getArticle();
		Mockito.when(articleRepositoryJPA.findById(ArgumentMatchers.any(Long.class))).thenReturn(Optional.of(this.mapper.fromArticle(article)));
		final Optional<Article> result = articleRepository.findById(article.getId());
		
		assertTrue(result.isPresent());
		assertEquals(article.getCode(), result.get().getCode());
		assertEquals(article.getDescription(), result.get().getDescription());
		assertEquals(article.getPrice(), result.get().getPrice());
		assertEquals(article.getId(), result.get().getId());
		assertEquals(article.getLastModificationDate(), result.get().getLastModificationDate());
	}
	
	@Test
	void shouldFindById_thenReturnEmpty() {
		Article article = ArticleProvider.getArticle();
		Mockito.when(articleRepositoryJPA.findById(ArgumentMatchers.any(Long.class))).thenReturn(Optional.empty());
		final Optional<Article> result = articleRepository.findById(article.getId());
		assertTrue(!result.isPresent());
	}
	
	@Test
	void shouldFindByCode_thenReturnArticle() {
		Article article = ArticleProvider.getArticle();
		Mockito.when(articleRepositoryJPA.findByCode(ArgumentMatchers.any(String.class))).thenReturn(Optional.of(this.mapper.fromArticle(article)));
		final Optional<Article> result = articleRepository.findByCode(article.getCode());

		assertTrue(result.isPresent());
		assertEquals(article.getCode(), result.get().getCode());
		assertEquals(article.getDescription(), result.get().getDescription());
		assertEquals(article.getPrice(), result.get().getPrice());
		assertEquals(article.getId(), result.get().getId());
		assertEquals(article.getLastModificationDate(), result.get().getLastModificationDate());
	}
	
	@Test
	void shouldFindByCode_thenReturnEmpty() {
		Article article = ArticleProvider.getArticle();
		Mockito.when(articleRepositoryJPA.findByCode(ArgumentMatchers.any(String.class))).thenReturn(Optional.empty());
		final Optional<Article> result = articleRepository.findByCode(article.getCode());
		assertTrue(!result.isPresent());
	}
	
	@Test
	void shouldDelete_thenDeleteIt() {
		Article article0 = ArticleProvider.getArticleToInsert();
		assertAll(() -> articleRepository.delete(article0));
	}
}
