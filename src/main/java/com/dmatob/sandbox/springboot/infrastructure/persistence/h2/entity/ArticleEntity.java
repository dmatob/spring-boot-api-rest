package com.dmatob.sandbox.springboot.infrastructure.persistence.h2.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@NamedEntityGraph(name = "article-entity", attributeNodes = {
		@NamedAttributeNode("type")
})
@Entity
@Table(name = "ARTICULO")
public class ArticleEntity {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "CODIGO", unique = true)
	private String code;

	@Column(name = "ID_ARTICULO_TIPO")
	private Long typeId;

	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "ID_ARTICULO_TIPO", insertable = false, updatable = false)
	private ArticleTypeEntity type;
	
	@Column(name = "DESCRIPCION")
	private String description;

	@Column(name = "PRECIO_EUROS")
	private BigDecimal price;

	@Column(name = "ULTIMA_MODIFICACION")
	private LocalDateTime lastModificationDate;

}
//