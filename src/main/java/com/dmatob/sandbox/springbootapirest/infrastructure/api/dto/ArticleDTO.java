package com.dmatob.sandbox.springbootapirest.infrastructure.api.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ArticleDTO implements Serializable {
	
	private static final long serialVersionUID = 6534848311628710555L;
	
	private Long id;
	private ArticleTypeDTO type;
	private String code;
	private String description;
	private BigDecimal price;
	private LocalDateTime lastModificationDate;
		
}
