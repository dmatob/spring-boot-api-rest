package es.sprinter.technicaltest.application.rest.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.Min;

public class ArticlePriceModificationRequestDTO implements Serializable {
	
	private static final long serialVersionUID = 8592964280619408197L;
		
	@Min(0)
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
