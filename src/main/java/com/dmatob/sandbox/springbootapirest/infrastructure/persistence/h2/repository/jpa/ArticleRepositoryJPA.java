package com.dmatob.sandbox.springbootapirest.infrastructure.persistence.h2.repository.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dmatob.sandbox.springbootapirest.infrastructure.persistence.h2.entity.ArticleEntity;

@Repository
public interface ArticleRepositoryJPA extends JpaRepository<ArticleEntity, Long> {

	@EntityGraph(value = "article-entity")
	List<ArticleEntity> findAll();

	@EntityGraph(value = "article-entity")
	public Optional<ArticleEntity> findByCode (String code);
	
}
