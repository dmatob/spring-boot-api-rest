package com.dmatob.sandbox.springbootapirest.infrastructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.dmatob.sandbox.springbootapirest.ArticlesApplication;
import com.dmatob.sandbox.springbootapirest.domain.repository.ArticleRepository;
import com.dmatob.sandbox.springbootapirest.domain.service.ArticleService;
import com.dmatob.sandbox.springbootapirest.domain.service.ArticleServiceImpl;

@Configuration
@ComponentScan(basePackageClasses = ArticlesApplication.class)
public class BeanConfiguration {

    @Bean
    ArticleService articleService(final ArticleRepository articleRepository) {
        return new ArticleServiceImpl(articleRepository);
    }
}