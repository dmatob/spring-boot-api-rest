package com.dmatob.sandbox.springbootapirest.infrastructure.persistence.h2.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name="ARTICULO_TIPO")
public class ArticleTypeEntity {
    
    @Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "CODIGO", unique = true)
	private String code;
	
	@Column(name = "DESCRIPCION")
	private String description;
    
}
