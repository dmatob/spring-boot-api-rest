package com.dmatob.sandbox.springbootapirest.infrastructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.dmatob.sandbox.springbootapirest.ArticlesApplication;
import com.dmatob.sandbox.springbootapirest.application.service.ArticleAppService;
import com.dmatob.sandbox.springbootapirest.application.service.ArticleAppServiceImpl;
import com.dmatob.sandbox.springbootapirest.domain.repository.ArticleRepository;
import com.dmatob.sandbox.springbootapirest.domain.repository.ArticleTypeRepository;

@Configuration
@ComponentScan(basePackageClasses = ArticlesApplication.class)
public class BeanConfiguration {

    @Bean
    ArticleAppService articleService(final ArticleRepository articleRepository, final ArticleTypeRepository articleTypeRepository) {
        return new ArticleAppServiceImpl(articleRepository, articleTypeRepository);
    }

}