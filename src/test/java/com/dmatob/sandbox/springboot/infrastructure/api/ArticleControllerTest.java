package com.dmatob.sandbox.springboot.infrastructure.api;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.dmatob.sandbox.springboot.application.exception.ArticleNotFoundException;
import com.dmatob.sandbox.springboot.application.exception.DuplicatedArticleException;
import com.dmatob.sandbox.springboot.application.service.ArticleAppService;
import com.dmatob.sandbox.springboot.domain.model.Article;
import com.dmatob.sandbox.springboot.domain.model.ArticleProvider;
import com.dmatob.sandbox.springboot.infrastructure.api.ArticleController;
import com.dmatob.sandbox.springboot.infrastructure.api.mapper.ArticleDTOMapper;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ArticleController.class)
class ArticleControllerTest {

	private ArticleDTOMapper articleDTOMapper = Mappers.getMapper(ArticleDTOMapper.class);

	@MockBean
	ArticleAppService articleService;

	@Autowired
	MockMvc mockMvc;

	@Test
	void shouldCreateArticle_thenReturnArticleOk() throws Exception {
		Article article = ArticleProvider.getArticleToInsert();
		Article articleWithId = Article.builder()
				.id(1l)
				.code(article.getCode())
				.description(article.getDescription())
				.price(article.getPrice())
				.build();
		Mockito.when(articleService.createArticle(ArgumentMatchers.any(Article.class))).thenReturn(articleWithId);

		mockMvc.perform(MockMvcRequestBuilders.post("/articles")
				.contentType(MediaType.APPLICATION_JSON)
				.content(ArticleJsonProvider.getArticleJSON(articleDTOMapper.toArticleModificationRequestDTO(article)))
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isCreated())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").isNotEmpty())
				.andExpect(MockMvcResultMatchers.jsonPath("$.code", Matchers.is(article.getCode())));
	}

	@Test
	void shouldCreateArticle_thenDuplicatedArticleException() throws Exception {
		Article article = ArticleProvider.getArticleToInsert();
		Mockito.when(articleService.createArticle(ArgumentMatchers.any(Article.class)))
				.thenThrow(DuplicatedArticleException.class);

		mockMvc.perform(MockMvcRequestBuilders.post("/articles")
				.contentType(MediaType.APPLICATION_JSON)
				.content(ArticleJsonProvider.getArticleJSON(articleDTOMapper.toArticleModificationRequestDTO(article)))
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isBadRequest())
				.andExpect(MockMvcResultMatchers.jsonPath("$").isNotEmpty());
	}

	@Test
	void shouldGetAllArticles_thenReturnAllArticlesOk() throws Exception {
		Article article0 = ArticleProvider.getArticle();
		Article article1 = ArticleProvider.getArticle();
		Article article2 = ArticleProvider.getArticle();
		List<Article> articles = Arrays.asList(article0, article1, article2);

		Mockito.when(articleService.getAllArticles()).thenReturn(articles);

		mockMvc.perform(MockMvcRequestBuilders.get("/articles")).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(3)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].code", Matchers.is(article0.getCode())))
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].code", Matchers.is(article1.getCode())))
				.andExpect(MockMvcResultMatchers.jsonPath("$[2].code", Matchers.is(article2.getCode())));
	}

	@Test
	void shouldGetAllArticles_thenReturnEmptyOk() throws Exception {
		Mockito.when(articleService.getAllArticles()).thenReturn(new ArrayList<Article>());

		mockMvc.perform(MockMvcRequestBuilders.get("/articles")).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0)));
	}

	@Test
	void shouldGetArticle_thenReturnArticleOk() throws Exception {
		Article article = ArticleProvider.getArticle();

		Mockito.when(articleService.getArticle(article.getCode())).thenReturn(article);

		mockMvc.perform(MockMvcRequestBuilders.get("/articles/" + article.getCode()))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.code", Matchers.is(article.getCode())));
	}

	@Test
	void shouldGetArticle_thenReturnEmptyOk() throws Exception {
		String randomArticleCode = "aaabbb";
		Mockito.when(articleService.getArticle(randomArticleCode)).thenReturn(null);

		mockMvc.perform(MockMvcRequestBuilders.get("/articles/" + randomArticleCode))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$").doesNotExist());
	}

	@Test
	void shouldUpdateArticle_thenReturnOk() throws Exception {
		Article article = ArticleProvider.getArticleToModify();
		Mockito.when(
				articleService.updateArticle(ArgumentMatchers.any(String.class), ArgumentMatchers.any(Article.class)))
				.thenReturn(article);

		mockMvc.perform(MockMvcRequestBuilders.put("/articles/example-code")
				.contentType(MediaType.APPLICATION_JSON)
				.content(ArticleJsonProvider.getArticleJSON(articleDTOMapper.toArticleModificationRequestDTO(article)))
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").isNotEmpty())
				.andExpect(MockMvcResultMatchers.jsonPath("$.code", Matchers.is(article.getCode())));
	}

	@Test
	void shouldUpdateArticle_thenArticleNotFoundException() throws Exception {
		Article article = ArticleProvider.getArticleToInsert();
		Mockito.when(
				articleService.updateArticle(ArgumentMatchers.any(String.class), ArgumentMatchers.any(Article.class)))
				.thenThrow(ArticleNotFoundException.class);

		mockMvc.perform(MockMvcRequestBuilders.put("/articles/example-code")
				.contentType(MediaType.APPLICATION_JSON)
				.content(ArticleJsonProvider.getArticleJSON(articleDTOMapper.toArticleModificationRequestDTO(article)))
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isNotFound())
				.andExpect(MockMvcResultMatchers.jsonPath("$").isNotEmpty());
	}

	@Test
	void shouldUpdatePriceArticle_thenReturnOk() throws Exception {
		Article article = ArticleProvider.getArticleToModify();
		Mockito.when(articleService.updatePriceArticleByCode(ArgumentMatchers.any(String.class),
				ArgumentMatchers.any(BigDecimal.class))).thenReturn(article);

		mockMvc.perform(MockMvcRequestBuilders.patch("/articles/example-code/price")
				.contentType(MediaType.APPLICATION_JSON)
				.content(ArticleJsonProvider.getArticleJSON(articleDTOMapper.toArticleModificationRequestDTO(article)))
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").isNotEmpty())
				.andExpect(MockMvcResultMatchers.jsonPath("$.code", Matchers.is(article.getCode())));
	}

	@Test
	void shouldUpdatePriceArticle_thenArticleNotFoundException() throws Exception {
		Article article = ArticleProvider.getArticleToInsert();
		Mockito.when(articleService.updatePriceArticleByCode(ArgumentMatchers.any(String.class),
				ArgumentMatchers.any(BigDecimal.class))).thenThrow(ArticleNotFoundException.class);

		mockMvc.perform(MockMvcRequestBuilders.patch("/articles/example-code/price")
				.contentType(MediaType.APPLICATION_JSON)
				.content(ArticleJsonProvider.getArticleJSON(articleDTOMapper.toArticleModificationRequestDTO(article)))
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isNotFound())
				.andExpect(MockMvcResultMatchers.jsonPath("$").isNotEmpty());
	}

	@Test
	void shouldDeleteArticle_thenReturnOk() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/articles/example-code"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	void shouldDeleteArticle_thenArticleNotFoundException() throws Exception {
		Mockito.doThrow(new ArticleNotFoundException("Articulo no encontrado")).when(articleService)
				.deleteArticleByCode(ArgumentMatchers.any(String.class));

		mockMvc.perform(MockMvcRequestBuilders.delete("/articles/example-code"))
				.andExpect(MockMvcResultMatchers.status().isNotFound())
				.andExpect(MockMvcResultMatchers.jsonPath("$").isNotEmpty());
	}

}
