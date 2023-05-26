package com.dmatob.sandbox.springbootapirest.infrastructure.api.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ArticleModificationRequestDTO implements Serializable {
	
	private static final long serialVersionUID = 8592964280619408197L;
	
	@NotBlank(message = "Es necesario indicar un código")
	@Size(max = 40, message = "El código ha de ser menor a 40 caracteres")
	private String code;
	
	@Size(max = 500, message = "La descripción ha de ser menor a 500 caracteres")
	private String description;
	
	@DecimalMin(value="0.0", inclusive = true, message = "EL precio ha de ser un valor positivo")
	private BigDecimal price;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
		return "ArticleModificationRequestDTO [code=" + code + ", description=" + description + ", price=" + price + "]";
	}
}
