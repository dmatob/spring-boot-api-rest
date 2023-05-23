package com.dmatob.sandbox.springbootapirest.infrastructure.persistence.h2;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepositoryJPA extends JpaRepository<ArticleEntity, Long> {

	public Optional<ArticleEntity> findByCode (String code);
	
}
