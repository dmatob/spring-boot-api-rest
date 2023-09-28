package com.dmatob.sandbox.springboot;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.dmatob.sandbox.springboot.application.service.ArticleAppService;
import com.dmatob.sandbox.springboot.domain.repository.ArticleRepository;
import com.dmatob.sandbox.springboot.domain.repository.ArticleTypeRepository;
import com.dmatob.sandbox.springboot.infrastructure.api.rest.ArticleController;
import com.dmatob.sandbox.springboot.infrastructure.persistence.h2.repository.jpa.ArticleRepositoryJPA;
import com.dmatob.sandbox.springboot.infrastructure.persistence.h2.repository.jpa.ArticleTypeRepositoryJPA;

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

  // @Autowired
  // ArticleTypeRepository articleTypeRepository;
  
  // @Autowired
  // ArticleTypeRepositoryJPA articleTypeRepositoryJPA;
  
  @Test
  void contextLoads() {   
    assertNotNull(articleController);
    assertNotNull(articleService);
    assertNotNull(articleRepository);
    assertNotNull(articleRepositoryJPA);
    // assertNotNull(articleTypeRepository);
    // assertNotNull(articleTypeRepositoryJPA);
  }
}