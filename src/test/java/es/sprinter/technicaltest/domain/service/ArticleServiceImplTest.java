package es.sprinter.technicaltest.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import es.sprinter.technicaltest.domain.Article;
import es.sprinter.technicaltest.domain.ArticleProvider;
import es.sprinter.technicaltest.domain.exception.ArticleNotFoundException;
import es.sprinter.technicaltest.domain.exception.DuplicatedArticleException;
import es.sprinter.technicaltest.domain.repository.ArticleRepository;

class ArticleServiceImplTest {

	private ArticleRepository articleRepository;
    private ArticleServiceImpl service;
	
	@BeforeEach
    void setUp() {
		articleRepository = mock(ArticleRepository.class);
        service = new ArticleServiceImpl(articleRepository);
    }
	
	@Test
    void shouldCreateArticle_thenSaveIt() {
        final Article article = ArticleProvider.getArticle();
        service.createArticle(article);
        verify(articleRepository).save(any(Article.class));
    }
	
	@Test
    void shouldCreateArticle_thenThrowDuplicatedArticleException () {		
		final Article article = ArticleProvider.getArticle();
		when(articleRepository.findByCode(article.getCode())).thenReturn(Optional.of(article));
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
		when(articleRepository.findAll()).thenReturn(articles);	
		List<Article> articlesResponse = service.getAllArticles();
		assertEquals(articles, articlesResponse);
    }
	
	
	@Test
    void shouldGetArticle_thenReturnData () {
		final Article article = ArticleProvider.getArticle();
		when(articleRepository.findByCode(article.getCode())).thenReturn(Optional.of(article));	
		Article returnedArticle = service.getArticle(article.getCode());
		assertEquals(article.getCode(), returnedArticle.getCode());
    }
	
	
	@Test
    void shouldGetArticle_thenReturnNull () {
		String randomCode = "aaaaaaaaa";
		when(articleRepository.findByCode(randomCode)).thenReturn(Optional.empty());	
		Article returnedArticle = service.getArticle(randomCode);
		assertNull(returnedArticle);
    }
	
	
	@Test
    void shouldUpdateArticle_thenSaveIt () {
		final Article article = ArticleProvider.getArticle();
		final Article newArticle = ArticleProvider.getArticle();
		when(articleRepository.findByCode(article.getCode())).thenReturn(Optional.of(article));
        service.updateArticle(article.getCode(), newArticle);
        verify(articleRepository).save(any(Article.class));
    }
	
	
	@Test
    void shouldUpdateArticle_thenArticleNotFoundException () {
		final Article article = ArticleProvider.getArticle();
		final Article newArticle = ArticleProvider.getArticle();
		when(articleRepository.findByCode(article.getCode())).thenReturn(Optional.empty());
        final Executable executable = () -> service.updateArticle(article.getCode(), newArticle);
        Assertions.assertThrows(ArticleNotFoundException.class, executable);
    }
	
	
	@Test
    void shouldUpdatePriceArticleByCode_thenSaveIt () {
		final Article article = ArticleProvider.getArticle();
		BigDecimal newPrice = new BigDecimal(45.55d).setScale(2, RoundingMode.HALF_UP);
		when(articleRepository.findByCode(article.getCode())).thenReturn(Optional.of(article));
        service.updatePriceArticleByCode(article.getCode(), newPrice);
        verify(articleRepository).save(any(Article.class));
    }
	
	@Test
    void shouldUpdatePriceArticleByCode_thenArticleNotFoundException () {
		final Article article = ArticleProvider.getArticle();
		BigDecimal newPrice = new BigDecimal(45.55d).setScale(2, RoundingMode.HALF_UP);
		when(articleRepository.findByCode(article.getCode())).thenReturn(Optional.empty());
        final Executable executable = () -> service.updatePriceArticleByCode(article.getCode(), newPrice);
        Assertions.assertThrows(ArticleNotFoundException.class, executable);
    }
	
	@Test
    void shouldDeleteArticleByCode_thenDeleteIt () {
		final Article article = ArticleProvider.getArticle();
		when(articleRepository.findByCode(article.getCode())).thenReturn(Optional.of(article));
		service.deleteArticleByCode(article.getCode());
        verify(articleRepository).delete(article);
    }
	
	@Test
    void shouldDeleteArticleByCode_thenArticleNotFoundException () {
		final Article article = ArticleProvider.getArticle();
		when(articleRepository.findByCode(article.getCode())).thenReturn(Optional.empty());
        final Executable executable = () -> service.deleteArticleByCode(article.getCode());
        Assertions.assertThrows(ArticleNotFoundException.class, executable);
    }
	
}
