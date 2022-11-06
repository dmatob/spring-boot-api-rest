package es.sprinter.technicaltest.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.sprinter.technicaltest.domain.exception.InvalidArticlePriceException;

public class Article {

	Logger logger = LoggerFactory.getLogger(Article.class);
	
	private Long id;
	private String code;
	private String description;
	private BigDecimal price;
	private LocalDateTime lastModificationDate;

	public Article(Long id, String code, String description, BigDecimal price, LocalDateTime lastModificationDate) {
		this.id = id;
		this.code = code;
		this.description = description;
		this.price = price;
		this.lastModificationDate = lastModificationDate;
	}

	public void changePrice(BigDecimal newPrice) {
		if (newPrice == null || newPrice.compareTo(BigDecimal.ZERO) < 0) {
			logger.error("Se ha producido un error cambiando el precio del articulo con codigo: {}. El precio siempre ha de ser mayor a cero", this.code);
			throw new InvalidArticlePriceException("El precio de un articulo siempre debe ser mayor a cero.");
		}
		logger.trace("Se va a proceder a cambiar el precio del articulo con codigo {}. {} --> {}", this.code, this.price, newPrice);

		this.price = newPrice;
		logger.debug("El precio del articulo con codigo {} ha cambiado a {}", this.code, this.price);
		
		this.updateLastModificationDate();
	}
	
	public void updateLastModificationDate () {
		this.lastModificationDate = LocalDateTime.now();
		logger.debug("Se ha actualizado la fecha de ultima modificacion del articulo con codigo {}", this.code);
	}

	public Long getId() {
		return id;
	}

	public String getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public LocalDateTime getLastModificationDate() {
		return lastModificationDate;
	}

	public void setLastModificationDate(LocalDateTime lastModificationDate) {
		this.lastModificationDate = lastModificationDate;
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", code=" + code + ", description=" + description + ", price=" + price
				+ ", lastModificationDate=" + lastModificationDate + "]";
	}

	
}
