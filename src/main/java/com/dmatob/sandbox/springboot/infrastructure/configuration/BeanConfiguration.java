package com.dmatob.sandbox.springboot.infrastructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.dmatob.sandbox.springboot.ArticlesApplication;
import com.dmatob.sandbox.springboot.application.service.ArticleAppService;
import com.dmatob.sandbox.springboot.application.service.ArticleAppServiceImpl;
import com.dmatob.sandbox.springboot.domain.repository.ArticleRepository;
import com.dmatob.sandbox.springboot.domain.repository.ArticleTypeRepository;

@Configuration
@ComponentScan(basePackageClasses = ArticlesApplication.class)
public class BeanConfiguration {

    @Bean
    ArticleAppService articleService(final ArticleRepository articleRepository, final ArticleTypeRepository articleTypeRepository) {
        return new ArticleAppServiceImpl(articleRepository, articleTypeRepository);
    }

}