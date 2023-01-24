package es.sprinter.technicaltest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import es.sprinter.technicaltest.application.rest.ArticleController;
import es.sprinter.technicaltest.domain.repository.ArticleRepository;
import es.sprinter.technicaltest.domain.service.ArticleService;
import es.sprinter.technicaltest.infrastructure.persistence.h2.ArticleRepositoryJPA;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ArticlesApplicationTests {
 
  @Autowired
  ArticleController articleController;
  
  @Autowired
  ArticleService articleService;
  
  @Autowired
  ArticleRepository articleRepository;
  
  @Autowired
  ArticleRepositoryJPA articleRepositoryJPA;
  
  @Test
  void contextLoads() {   
    assertNotNull(articleController);
    assertNotNull(articleService);
    assertNotNull(articleRepository);
    assertNotNull(articleRepositoryJPA);
  }
}