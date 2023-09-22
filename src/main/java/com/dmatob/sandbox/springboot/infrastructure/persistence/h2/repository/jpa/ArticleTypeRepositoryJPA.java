package com.dmatob.sandbox.springboot.infrastructure.persistence.h2.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dmatob.sandbox.springboot.infrastructure.persistence.h2.entity.ArticleTypeEntity;

@Repository
public interface ArticleTypeRepositoryJPA extends JpaRepository<ArticleTypeEntity, Long> {
    
}
