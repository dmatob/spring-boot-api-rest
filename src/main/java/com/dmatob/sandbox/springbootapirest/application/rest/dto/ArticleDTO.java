package com.dmatob.sandbox.springbootapirest.application.rest.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ArticleDTO implements Serializable {
	
	private static final long serialVersionUID = 6534848311628710555L;
	
	private Long id;
	private String code;
	private String description;
	private BigDecimal price;
	private LocalDateTime lastModificationDate;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
	public LocalDateTime getLastModificationDate() {
		return lastModificationDate;
	}
	public void setLastModificationDate(LocalDateTime lastModificationDate) {
		this.lastModificationDate = lastModificationDate;
	}
	@Override
	public String toString() {
		return "ArticleDTO [id=" + id + ", code=" + code + ", description=" + description + ", price=" + price
				+ ", lastModificationDate=" + lastModificationDate + "]";
	}
	
	
	
}
