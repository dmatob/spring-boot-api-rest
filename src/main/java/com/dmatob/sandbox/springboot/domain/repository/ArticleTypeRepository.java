package com.dmatob.sandbox.springboot.domain.repository;

import java.util.Optional;

import com.dmatob.sandbox.springboot.domain.model.ArticleType;

public interface ArticleTypeRepository {
	Optional<ArticleType> findById(Long id);
}