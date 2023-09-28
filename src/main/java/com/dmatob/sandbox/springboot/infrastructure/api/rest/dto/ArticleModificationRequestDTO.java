package com.dmatob.sandbox.springboot.infrastructure.api.rest.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ArticleModificationRequestDTO implements Serializable {
	
	private static final long serialVersionUID = 8592964280619408197L;
	
	@NotBlank(message = "Es necesario indicar un código")
	@Size(max = 40, message = "El código ha de ser menor a 40 caracteres")
	private String code;

	@NotNull(message = "Es necesario indicar un tipo de articulo")
	private Long typeId;
	
	@Size(max = 500, message = "La descripción ha de ser menor a 500 caracteres")
	private String description;
	
	@DecimalMin(value="0.0", inclusive = true, message = "EL precio ha de ser un valor positivo")
	private BigDecimal price;
	
}
