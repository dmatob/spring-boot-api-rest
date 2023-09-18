package com.dmatob.sandbox.springbootapirest.domain.repository;

import java.util.Optional;

import com.dmatob.sandbox.springbootapirest.domain.model.ArticleType;

public interface ArticleTypeRepository {
	Optional<ArticleType> findById(Long id);
}