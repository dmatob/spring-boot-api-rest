package es.sprinter.technicaltest.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import es.sprinter.technicaltest.domain.exception.InvalidArticlePriceException;

class ArticleUnitTest {

	@Test
	void shouldChangePrice_thenUpdatePriceAndLastModificationDate () {
		Article article = ArticleProvider.getArticle();
		
		Long prevId = article.getId();
		String prevCode = article.getCode();
		String prevDescription = article.getDescription();
		LocalDateTime prevLastModificationDate = article.getLastModificationDate();
		
		BigDecimal newPrice = new BigDecimal(45.55d).setScale(2, RoundingMode.HALF_UP);
		article.changePrice(newPrice);
		
		// El precio se ha modificado al valor esperado
		assertEquals(newPrice, article.getPrice());
		
		// La fecha de modificación se modifica tras la modificación del precio .
		assertNotEquals(prevLastModificationDate, article.getLastModificationDate());
		
		// Nos e ha modificado ninguna otra propiedad del articulo
		assertEquals(prevId, article.getId());
		assertEquals(prevCode, article.getCode());
		assertEquals(prevDescription, article.getDescription());
	}
	
	
	@Test
	void shouldChangePrice_thenThrowInvalidArticlePriceException () {
		Article article = ArticleProvider.getArticle();
				
		BigDecimal newInvalidPrice = new BigDecimal(-4.30d).setScale(2, RoundingMode.HALF_UP);
		
		final Executable executable = () -> article.changePrice(newInvalidPrice);
		
		Assertions.assertThrows(InvalidArticlePriceException.class, executable);
	}
	
	
	@Test
	void shouldChangePrice_thenNoPriceModification () {
		Article article = ArticleProvider.getArticle();
		
		Long prevId = article.getId();
		String prevCode = article.getCode();
		String prevDescription = article.getDescription();
		BigDecimal prevPrice = article.getPrice();
		LocalDateTime prevLastModificationDate = article.getLastModificationDate();
		
		BigDecimal newPrice = null;
		article.changePrice(newPrice);
		
		// Nos e ha modificado ninguna propiedad del articulo
		assertEquals(prevId, article.getId());
		assertEquals(prevCode, article.getCode());
		assertEquals(prevDescription, article.getDescription());
		assertEquals(prevPrice, article.getPrice());
		assertEquals(prevLastModificationDate, article.getLastModificationDate());
	}
	
	
	@Test
	void shouldUpdateLastModificationDate_thenUpdateLastModificationDate () {
		Article article = ArticleProvider.getArticle();
		
		Long prevId = article.getId();
		String prevCode = article.getCode();
		BigDecimal prevPrice = article.getPrice();
		String prevDescription = article.getDescription();
		
		article.updateLastModificationDate();
		
		// La fecha de modificación se modifica tras la modificación del precio y es actual.
		assertNotNull(article.getLastModificationDate());
		assertTrue(article.getLastModificationDate().compareTo(LocalDateTime.now()) <= 0);
		assertTrue(article.getLastModificationDate().compareTo(LocalDateTime.now().minusMinutes(2)) >= 0);
		
		// Nos e ha modificado ninguna otra propiedad del articulo
		assertEquals(prevId, article.getId());
		assertEquals(prevCode, article.getCode());
		assertEquals(prevDescription, article.getDescription());
		assertEquals(prevPrice, article.getPrice());
	}
	
}
