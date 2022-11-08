package es.sprinter.technicaltest.infrastructure.persistence.h2;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ARTICULO")
public class ArticleEntity {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "CODIGO", unique = true)
	private String code;
	
	@Column(name = "DESCRIPCION")
	private String description;
	
	@Column(name = "PRECIO_EUROS")
	private BigDecimal price;
	
	@Column(name = "ULTIMA_MODIFICACION")
	private LocalDateTime lastModificationDate;

	public ArticleEntity() {
		super();
	}
	
	public ArticleEntity(Long id, String code, String description, BigDecimal price, LocalDateTime lastModificationDate) {
		super();
		this.id = id;
		this.code = code;
		this.description = description;
		this.price = price;
	}

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
		return "ArticleEntity [id=" + id + ", code=" + code + ", description=" + description + ", price=" + price
				+ ", lastModificationDate=" + lastModificationDate + "]";
	}
}
