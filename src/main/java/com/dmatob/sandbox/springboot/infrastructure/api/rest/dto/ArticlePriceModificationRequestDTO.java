package com.dmatob.sandbox.springboot.infrastructure.api.rest.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;


public class ArticlePriceModificationRequestDTO implements Serializable {
	
	private static final long serialVersionUID = 8592964280619408197L;
		
	@DecimalMin(value="0.0", inclusive = true, message = "EL precio ha de ser un valor positivo")
	private BigDecimal price;
	
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
		return "ArticlePriceModificationRequestDTO [ price=" + price + "]";
	}
}
