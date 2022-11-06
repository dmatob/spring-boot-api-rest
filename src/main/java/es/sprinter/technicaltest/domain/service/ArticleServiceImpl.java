package es.sprinter.technicaltest.domain.service;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import es.sprinter.technicaltest.domain.Article;
import es.sprinter.technicaltest.domain.exception.ArticleNotFoundException;
import es.sprinter.technicaltest.domain.exception.DuplicatedArticleException;
import es.sprinter.technicaltest.domain.repository.ArticleRepository;

@Service
public class ArticleServiceImpl implements ArticleService {

	Logger logger = LoggerFactory.getLogger(ArticleServiceImpl.class);
	
	private final ArticleRepository articleRepository;

	public ArticleServiceImpl(ArticleRepository articleRepository) {
		this.articleRepository = articleRepository;
	}

	@Override
	@CacheEvict(value="articles")
	public Article createArticle(Article article) {
		
		Article articleSearch = getArticle(article.getCode());
		if (articleSearch != null) {
			logger.error("No se puede dar de alta el articulo con codigo: {}. Articulo duplicado", article.getCode());
			throw new DuplicatedArticleException("El articulo ya existe");
		}
		
		return this.articleRepository.save(article);
	}

	@Override
	@Cacheable("articles")
	public List<Article> getAllArticles() {
		
		logger.info("Se va a proceder a obtener el listado de todos los articulos disponibles");
		
		List<Article> lstArticles = this.articleRepository.findAll();
		
		logger.debug("Se han obtenido un total de {} resultados", lstArticles != null ? lstArticles.size() : 0);
		logger.trace("Listado completo de articulos. {}", lstArticles);
		
		return lstArticles;
	}

	@Override
	@Cacheable("articles")
	public Article getArticle(String code) {
		
		logger.info("Se va a proceder a obtener información del artículo con codigo: {}", code);
		
		Article article = this.articleRepository.findByCode(code).orElse(null);
		
		logger.debug("La consulta del articulo con codigo: {} ¿ha devuelto datos? {}", code, article != null);
		logger.trace("Informacion del articulo con codigo: {}. Datos: {}", code, article);
		return article;
	}

	@Override
	@CacheEvict(value="articles", key="code")
	public Article updateArticle(String code, Article article) {
		
		logger.debug("Se va a proceder a modificar el articulo con codigo: {}. Datos a modificar : {}", code, article);
		
		Article currentArticle = getArticle(code);
		if (currentArticle == null) {
			logger.error("Error actualizando el articulo con codigo: {}. El articulo no existe.", code);
			throw new ArticleNotFoundException("No se puede actulizar el articulo con el codigo proporcionado");
		}
		article.updateLastModificationDate();
		Article modifiedArticle = this.articleRepository.save(article);
		
		logger.info("Se ha actualizado el articulo con codigo: {}.", code);
		logger.trace("Resultado de la actualizacion del articulo con codigo: {}. {}: ", code, modifiedArticle);
		
		return modifiedArticle;
	}

	@Override
	@CacheEvict(value="articles", key="code")
	public Article updatePriceArticleByCode(String code, BigDecimal price) {
		
		logger.debug("Se va a proceder a modificar el precio del articulo con codigo: {}. Precio a modificar : {}", code, price);
		
		Article article = getArticle(code);
		if (article == null) {
			logger.error("Error actualizando el precio del articulo con codigo: {}. El articulo no existe.", code);
			throw new ArticleNotFoundException("No se puede actulizar el precio del articulo con el codigo proporcionado");
		}
		article.changePrice(price);
		Article modifiedArticle = this.articleRepository.save(article);
		
		logger.info("Se ha actualizado el precio del articulo con codigo: {}.", code);
		logger.trace("Resultado de la actualizacion de precio del articulo con codigo: {}. {}", code, modifiedArticle);
		
		return modifiedArticle;
	}

	@Override
	@CacheEvict(value="articles", key="code")
	public void deleteArticleByCode(String code) {
		
		logger.debug("Se va a proceder a eliminar el articulo con codigo: {}", code);
		
		Article article = getArticle(code);
		if (article == null) {
			logger.error("Error eliminando el articulo con codigo: {}. El articulo no existe", code);
			throw new ArticleNotFoundException("No se puede eliminar el articulo con el codigo proporcionado");
		}
		this.articleRepository.delete(article);
		
		logger.info("Se ha eliminado el articulo con codigo: {}", code);
	}

}
