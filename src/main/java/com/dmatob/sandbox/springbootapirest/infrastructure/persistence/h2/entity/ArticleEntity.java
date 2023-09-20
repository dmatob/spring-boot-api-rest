package com.dmatob.sandbox.springbootapirest.infrastructure.persistence.h2.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.Table;

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