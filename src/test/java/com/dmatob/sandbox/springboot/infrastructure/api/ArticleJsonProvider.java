package com.dmatob.sandbox.springboot.infrastructure.api;

import java.util.Random;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.dmatob.sandbox.springboot.infrastructure.api.ArticleController;
import com.dmatob.sandbox.springboot.infrastructure.api.dto.ArticleModificationRequestDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ArticleController.class)
class ArticleJsonProvider {

	public static Random random = new Random();

	public static String getArticleJSON(ArticleModificationRequestDTO article) throws JsonProcessingException {
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		return ow.writeValueAsString(article);
	}

}
