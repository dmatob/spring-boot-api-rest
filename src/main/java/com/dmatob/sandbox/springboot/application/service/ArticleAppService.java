package com.dmatob.sandbox.springboot.application.service;

import java.math.BigDecimal;
import java.util.List;

import com.dmatob.sandbox.springboot.domain.model.Article;

public interface ArticleAppService {

	/**
	 * Método que gestiona el alta de un nuevo artículo.
	 * 
	 * @param article Objeto de dominio que contiene información del articulo
	 * @return Objeto con la información del articulo dado de alta.
	 * @throws DuplicatedArticleException Excepción lanzada cuando ya existe un
	 *                                    artículo con el mismo código (clave única
	 *                                    en el modelo de datos)
	 */
	Article createArticle(Article article);

	/**
	 * Método que devuelve el listado de todos los artículos existentes.
	 * 
	 * @return Listado de todos los articulos existentes.
	 */
	List<Article> getAllArticles();

	/**
	 * Método que devuelve la información de un artículo en base a su código (clave
	 * única en el modelo de datos).
	 * 
	 * @param code Código del articulo del que se desea obtener información
	 * @return Objeto de dominio con la información del artículo o null en caso de
	 *         que no se encuentre un artículo con el código remitdo.
	 */
	Article getArticle(String code);

	/**
	 * Método que gestiona la actualización de un artículo
	 * 
	 * @param code    Código del articulo del que se desea obtener información
	 * @param article Objeto de dominio con la información del artículo modificado.
	 * @return Objeto de dominio con la información del artículo modificado
	 * @throws ArticleNotFoundException No existe artículo con el código indicado.
	 * @trhows InvalidArticlePriceException Excepción lanzada si el precio es un
	 *         valor negativo.
	 */
	Article updateArticle(String code, Article article);

	/**
	 * Método que gestiona la actualización de precio de un artículo
	 * 
	 * @param code  Código del articulo del que se desea obtener información
	 * @param price Nuevo precio del artículo
	 * @return Objeto de dominio con la información del artículo modificado
	 * @throws ArticleNotFoundException No existe artículo con el código indicado.
	 * @trhows InvalidArticlePriceException Excepción lanzada si el precio es un
	 *         valor negativo.
	 */
	Article updatePriceArticleByCode(String code, BigDecimal price);

	/**
	 * Método que gestiona la eliminación de un artículo (Baja física)
	 * 
	 * @param code Código del articulo que se desea dar de baja
	 * @throws ArticleNotFoundException No existe artículo con el código indicado.
	 */
	void deleteArticleByCode(String code);
}
