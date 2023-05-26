package com.dmatob.sandbox.springbootapirest.infrastructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.dmatob.sandbox.springbootapirest.ArticlesApplication;
import com.dmatob.sandbox.springbootapirest.application.service.ArticleAppService;
import com.dmatob.sandbox.springbootapirest.application.service.ArticleAppServiceImpl;
import com.dmatob.sandbox.springbootapirest.domain.repository.ArticleRepository;

@Configuration
@ComponentScan(basePackageClasses = ArticlesApplication.class)
public class BeanConfiguration {

    @Bean
    ArticleAppService articleService(final ArticleRepository articleRepository) {
        return new ArticleAppServiceImpl(articleRepository);
    }
}