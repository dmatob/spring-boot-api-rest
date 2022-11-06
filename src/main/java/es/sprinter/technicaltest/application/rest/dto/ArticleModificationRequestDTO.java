package es.sprinter.technicaltest.application.rest.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ArticleModificationRequestDTO implements Serializable {
	
	private static final long serialVersionUID = 8592964280619408197L;
	
	@NotBlank
	@Size(max = 40)
	private String code;
	
	@Size(max = 500)
	private String description;
	
	@Min(0)
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
