package com.dmatob.sandbox.springbootapirest.application.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import com.dmatob.sandbox.springbootapirest.application.exception.ArticleNotFoundException;
import com.dmatob.sandbox.springbootapirest.application.exception.DuplicatedArticleException;
import com.dmatob.sandbox.springbootapirest.domain.model.Article;
import com.dmatob.sandbox.springbootapirest.domain.model.ArticleProvider;
import com.dmatob.sandbox.springbootapirest.domain.repository.ArticleRepository;

class ArticleAppServiceImplTest {

	private ArticleRepository articleRepository;
    private ArticleAppServiceImpl service;
    
	@BeforeEach
    void setUp() {
		articleRepository = Mockito.mock(ArticleRepository.class);
        service = new ArticleAppServiceImpl(articleRepository);
    }
	
	@Test
    void shouldCreateArticle_thenSaveIt() {
        final Article article = ArticleProvider.getArticleToInsert();
        Mockito.when(articleRepository.save(ArgumentMatchers.any(Article.class))).thenAnswer(i -> i.getArgument(0));
        Article response = service.createArticle(article);
        Mockito.verify(articleRepository).save(article);
        Assertions.assertEquals(response, article);
    }
	
	@Test
    void shouldCreateArticle_thenThrowDuplicatedArticleException () {		
		final Article article = ArticleProvider.getArticleToInsert();
		Mockito.when(articleRepository.findByCode(article.getCode())).thenReturn(Optional.of(article));
        final Executable executable = () -> service.createArticle(article);
        Assertions.assertThrows(DuplicatedArticleException.class, executable);
    }
	
	@Test
    void shouldGetAllArticles_thenReturnData () {		
		final Article article0 = ArticleProvider.getArticle();
		final Article article1 = ArticleProvider.getArticle();
		final Article article2 = ArticleProvider.getArticle();
		final Article article3 = ArticleProvider.getArticle();
		final Article article4 = ArticleProvider.getArticle();
		List<Article> articles = Arrays.asList(article0, article1, article2, article3, article4);
		Mockito.when(articleRepository.findAll()).thenReturn(articles);	
		List<Article> articlesResponse = service.getAllArticles();
		Assertions.assertEquals(articles, articlesResponse);
    }
	
	
	@Test
    void shouldGetArticle_thenReturnData () {
		final Article article = ArticleProvider.getArticle();
		Mockito.when(articleRepository.findByCode(article.getCode())).thenReturn(Optional.of(article));	
		Article returnedArticle = service.getArticle(article.getCode());
		Assertions.assertEquals(article.getCode(), returnedArticle.getCode());
    }
	
	
	@Test
    void shouldGetArticle_thenReturnNull () {
		String randomCode = "aaaaaaaaa";
		Mockito.when(articleRepository.findByCode(randomCode)).thenReturn(Optional.empty());	
		Article returnedArticle = service.getArticle(randomCode);
		Assertions.assertNull(returnedArticle);
    }
	
	
	@Test
    void shouldUpdateArticle_thenSaveIt () {
		final Article article = ArticleProvider.getArticleToModify();
		final Article newArticle = ArticleProvider.getArticle();
		Mockito.when(articleRepository.findByCode(article.getCode())).thenReturn(Optional.of(article));
		Mockito.when(articleRepository.save(ArgumentMatchers.any(Article.class))).thenAnswer(i -> i.getArgument(0));
        Article serviceResponse = service.updateArticle(article.getCode(), newArticle);
        Mockito.verify(articleRepository).save(ArgumentMatchers.any(Article.class));
        Assertions.assertEquals(serviceResponse.getId(), article.getId());
        Assertions.assertEquals(serviceResponse.getCode(), newArticle.getCode());
        Assertions.assertEquals(serviceResponse.getDescription(), newArticle.getDescription());
    }
	
	
	@Test
    void shouldUpdateArticle_thenArticleNotFoundException () {
		final Article article = ArticleProvider.getArticleToModify();
		final Article newArticle = ArticleProvider.getArticle();
		Mockito.when(articleRepository.findByCode(article.getCode())).thenReturn(Optional.empty());
        final Executable executable = () -> service.updateArticle(article.getCode(), newArticle);
        Assertions.assertThrows(ArticleNotFoundException.class, executable);
    }
	
	
	@Test
    void shouldUpdatePriceArticleByCode_thenSaveIt () {
		final Article article = ArticleProvider.getArticleToModify();
		BigDecimal newPrice = new BigDecimal(45.55d).setScale(2, RoundingMode.HALF_UP);
		Mockito.when(articleRepository.findByCode(article.getCode())).thenReturn(Optional.of(article));
		Mockito.when(articleRepository.save(ArgumentMatchers.any(Article.class))).thenAnswer(i -> i.getArgument(0));
        Article modifiedArticle = service.updatePriceArticleByCode(article.getCode(), newPrice);
        Mockito.verify(articleRepository).save(ArgumentMatchers.any(Article.class));
        
        Assertions.assertEquals(modifiedArticle.getId(), article.getId());
        Assertions.assertEquals(modifiedArticle.getCode(), article.getCode());
        Assertions.assertEquals(modifiedArticle.getDescription(), article.getDescription());
        Assertions.assertEquals(modifiedArticle.getPrice(), newPrice);
    }
	
	@Test
    void shouldUpdatePriceArticleByCode_thenArticleNotFoundException () {
		final Article article = ArticleProvider.getArticleToModify();
		BigDecimal newPrice = new BigDecimal(45.55d).setScale(2, RoundingMode.HALF_UP);
		Mockito.when(articleRepository.findByCode(article.getCode())).thenReturn(Optional.empty());
        final Executable executable = () -> service.updatePriceArticleByCode(article.getCode(), newPrice);
        Assertions.assertThrows(ArticleNotFoundException.class, executable);
    }
	
	@Test
    void shouldDeleteArticleByCode_thenDeleteIt () {
		final Article article = ArticleProvider.getArticle();
		Mockito.when(articleRepository.findByCode(article.getCode())).thenReturn(Optional.of(article));
		service.deleteArticleByCode(article.getCode());
        Mockito.verify(articleRepository).delete(article);
    }
	
	@Test
    void shouldDeleteArticleByCode_thenArticleNotFoundException () {
		final Article article = ArticleProvider.getArticle();
		Mockito.when(articleRepository.findByCode(article.getCode())).thenReturn(Optional.empty());
        final Executable executable = () -> service.deleteArticleByCode(article.getCode());
        Assertions.assertThrows(ArticleNotFoundException.class, executable);
    }
	
}
