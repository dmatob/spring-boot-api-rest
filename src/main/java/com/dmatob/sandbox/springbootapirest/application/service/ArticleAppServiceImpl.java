package com.dmatob.sandbox.springbootapirest.application.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;

import com.dmatob.sandbox.springbootapirest.application.exception.ArticleNotFoundException;
import com.dmatob.sandbox.springbootapirest.application.exception.DuplicatedArticleException;
import com.dmatob.sandbox.springbootapirest.domain.model.Article;
import com.dmatob.sandbox.springbootapirest.domain.repository.ArticleRepository;

public class ArticleAppServiceImpl implements ArticleAppService {

	private static final Logger LOG = LoggerFactory.getLogger(ArticleAppServiceImpl.class);
	
	private final ArticleRepository articleRepository;

	public ArticleAppServiceImpl(ArticleRepository articleRepository) {
		this.articleRepository = articleRepository;
	}

	@Override
	@CacheEvict(value="articles", allEntries = true)
	public Article createArticle(Article article) {
		
		Article articleSearch = getArticle(article.getCode());
		if (articleSearch != null) {
			LOG.error("No se puede dar de alta el articulo con codigo: {}. Articulo duplicado", article.getCode());
			throw new DuplicatedArticleException("El articulo ya existe");
		}
		
		return this.articleRepository.save(article);
	}

	@Override
	@Cacheable("articles")
	public List<Article> getAllArticles() {
		
		LOG.info("Se va a proceder a obtener el listado de todos los articulos disponibles");
		
		List<Article> lstArticles = this.articleRepository.findAll();
		
		LOG.debug("Se han obtenido un total de {} resultados", lstArticles);
		LOG.trace("Listado completo de articulos. {}", lstArticles);
		
		return lstArticles;
	}

	@Override
	@Cacheable("article")
	public Article getArticle(String code) {
		
		LOG.info("Se va a proceder a obtener información del artículo con codigo: {}", code);
		
		Article article = this.articleRepository.findByCode(code).orElse(null);
		
		LOG.debug("La consulta del articulo con codigo: {} ¿ha devuelto datos? {}", code, article != null);
		LOG.trace("Informacion del articulo con codigo: {}. Datos: {}", code, article);
		return article;
	}

	@Override
	@Caching(evict = {
            @CacheEvict(value="articles", allEntries=true),
            @CacheEvict(value="article", key = "#code")
	})
	public Article updateArticle(String code, Article article) {
		
		LOG.debug("Se va a proceder a modificar el articulo con codigo: {}. Datos a modificar : {}", code, article);
		
		Article currentArticle = getArticle(code);
		if (currentArticle == null) {
			LOG.error("Error actualizando el articulo con codigo: {}. El articulo no existe.", code);
			throw new ArticleNotFoundException("No se puede actulizar el articulo con el codigo proporcionado");
		}
		Article articleToModify = new Article(currentArticle.getId(), 
				article.getCode(), article.getDescription(), article.getPrice(), LocalDateTime.now());
		Article modifiedArticle = this.articleRepository.save(articleToModify);
		
		LOG.info("Se ha actualizado el articulo con codigo: {}.", code);
		LOG.trace("Resultado de la actualizacion del articulo con codigo: {}. {}: ", code, modifiedArticle);
		
		return modifiedArticle;
	}

	@Override
	@Caching(evict = {
            @CacheEvict(value="articles", allEntries=true),
            @CacheEvict(value="article", key = "#code")
	})
	public Article updatePriceArticleByCode(String code, BigDecimal price) {
		
		LOG.debug("Se va a proceder a modificar el precio del articulo con codigo: {}. Precio a modificar : {}", code, price);
		
		Article article = getArticle(code);
		if (article == null) {
			LOG.error("Error actualizando el precio del articulo con codigo: {}. El articulo no existe.", code);
			throw new ArticleNotFoundException("No se puede actulizar el precio del articulo con el codigo proporcionado");
		}
		article.changePrice(price);
		Article modifiedArticle = this.articleRepository.save(article);
		
		LOG.info("Se ha actualizado el precio del articulo con codigo: {}.", code);
		LOG.trace("Resultado de la actualizacion de precio del articulo con codigo: {}. {}", code, modifiedArticle);
		
		return modifiedArticle;
	}

	@Override
	@Caching(evict = {
            @CacheEvict(value="articles", allEntries=true),
            @CacheEvict(value="article", key = "#code")
	})
	public void deleteArticleByCode(String code) {
		
		LOG.debug("Se va a proceder a eliminar el articulo con codigo: {}", code);
		
		Article article = getArticle(code);
		if (article == null) {
			LOG.error("Error eliminando el articulo con codigo: {}. El articulo no existe", code);
			throw new ArticleNotFoundException("No se puede eliminar el articulo con el codigo proporcionado");
		}
		this.articleRepository.delete(article);
		
		LOG.info("Se ha eliminado el articulo con codigo: {}", code);
	}

}
