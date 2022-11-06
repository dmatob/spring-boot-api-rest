package es.sprinter.technicaltest.application.rest;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.sprinter.technicaltest.application.rest.dto.ArticleDTO;
import es.sprinter.technicaltest.application.rest.dto.ArticleModificationRequestDTO;
import es.sprinter.technicaltest.application.rest.dto.ArticlePriceModificationRequestDTO;
import es.sprinter.technicaltest.domain.service.ArticleService;

@RestController
@RequestMapping("/articles")
public class ArticleController {

	Logger logger = LoggerFactory.getLogger(ArticleController.class);
	
	private final ArticleService articleService;
	
	private final ArticleDTOMapper articleDTOMapper;
	
	public ArticleController(ArticleService articleService, ArticleDTOMapper articleDTOMapper) {
		this.articleService = articleService;
		this.articleDTOMapper = articleDTOMapper;
	}
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<ArticleDTO> createArticle(@RequestBody @Valid final ArticleModificationRequestDTO articleDataDTO) {
		logger.info("Llamada a la API de articulos para crear un nuevo articulo");
		return new ResponseEntity<>(this.articleDTOMapper.toArticleDTO(this.articleService.createArticle(this.articleDTOMapper.fromArticleDTO(articleDataDTO))), HttpStatus.CREATED);
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<List<ArticleDTO>> getAllArticles() {
		logger.info("Llamada a la API de articulos para obtener el listado de todos los articulos disponibles");
		return ResponseEntity.ok(this.articleDTOMapper.toListOfArticleDTOs(this.articleService.getAllArticles()));
	}
	
	@GetMapping(value = "/{articleCode}", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<ArticleDTO> getArticleByCode(@PathVariable final String articleCode) {
		logger.info("Llamada a la API de articulos para obtener informacion del articulo con codigo {}", articleCode);
		return ResponseEntity.ok(this.articleDTOMapper.toArticleDTO(this.articleService.getArticle(articleCode)));
	}
	
	@PutMapping(value = "/{articleCode}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<ArticleDTO> updateArticle(@PathVariable final String articleCode, @RequestBody @Valid final ArticleModificationRequestDTO articleDataDTO) {
		logger.info("Llamada a la API de articulos para modificar la informacion del articulo con codigo: {}", articleCode);
		return new ResponseEntity<>(this.articleDTOMapper.toArticleDTO(this.articleService.updateArticle(articleCode, this.articleDTOMapper.fromArticleDTO(articleDataDTO))), HttpStatus.OK);
	}
	
	@PatchMapping(value = "/{articleCode}/price", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<ArticleDTO> updatePriceArticle(@PathVariable final String articleCode, @RequestBody @Valid final ArticlePriceModificationRequestDTO articleDataDTO) {
		logger.info("Llamada a la API de articulos para modificar el precio del articulo con codigo: {}", articleCode);
		return new ResponseEntity<>(this.articleDTOMapper.toArticleDTO(this.articleService.updatePriceArticleByCode(articleCode, articleDataDTO.getPrice())), HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{articleCode}")
	ResponseEntity<Void> deleteArticle(@PathVariable final String articleCode) {
		logger.info("Llamada a la API de articulos para eliminar el articulo con codigo: {}", articleCode);
		this.articleService.deleteArticleByCode(articleCode);
		return ResponseEntity.noContent().build();
	}


}
