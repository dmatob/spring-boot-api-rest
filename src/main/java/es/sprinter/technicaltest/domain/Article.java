package es.sprinter.technicaltest.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.sprinter.technicaltest.domain.exception.InvalidArticlePriceException;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Article {

	private static final Logger LOG = LoggerFactory.getLogger(Article.class);
	
	private Long id;
	private String code;
	private String description;
	private BigDecimal price;
	private LocalDateTime lastModificationDate;

	public Article(Long id, String code, String description, BigDecimal price, LocalDateTime lastModificationDate) {
		this.id = id;
		this.code = code;
		this.description = description;	
		this.lastModificationDate = lastModificationDate;
		changePrice(price);
	}

	public void changePrice(BigDecimal newPrice) {
		if (newPrice != null) {
			if (newPrice.compareTo(BigDecimal.ZERO) < 0) {
				LOG.error("Se ha producido un error cambiando el precio del articulo con codigo: {}. El precio siempre ha de ser mayor a cero", this.code);
				throw new InvalidArticlePriceException("El precio de un articulo siempre debe ser mayor a cero.");
			}
			LOG.trace("Se va a proceder a cambiar el precio del articulo con codigo {}. {} --> {}", this.code, this.price, newPrice);
	
			this.price = newPrice.setScale(2, RoundingMode.HALF_UP);
			LOG.debug("El precio del articulo con codigo {} ha cambiado a {}", this.code, this.price);
			
			this.updateLastModificationDate();
		}
	}
	
	public void updateLastModificationDate () {
		this.lastModificationDate = LocalDateTime.now();
		LOG.debug("Se ha actualizado la fecha de ultima modificacion del articulo con codigo {}", this.code);
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

	@Override
	public String toString() {
		return "Article [id=" + id + ", code=" + code + ", description=" + description + ", price=" + price
				+ ", lastModificationDate=" + lastModificationDate + "]";
	}
	
}
