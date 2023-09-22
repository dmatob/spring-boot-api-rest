package com.dmatob.sandbox.springboot;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.dmatob.sandbox.springboot.application.service.ArticleAppService;
import com.dmatob.sandbox.springboot.domain.repository.ArticleRepository;
import com.dmatob.sandbox.springboot.infrastructure.api.ArticleController;
import com.dmatob.sandbox.springboot.infrastructure.persistence.h2.repository.jpa.ArticleRepositoryJPA;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ArticlesApplicationTests {
 
  @Autowired
  ArticleController articleController;
  
  @Autowired
  ArticleAppService articleService;
  
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