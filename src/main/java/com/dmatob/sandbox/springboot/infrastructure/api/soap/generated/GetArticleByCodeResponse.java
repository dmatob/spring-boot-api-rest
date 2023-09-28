//
// Este archivo ha sido generado por Eclipse Implementation of JAXB v3.0.0 
// Visite https://eclipse-ee4j.github.io/jaxb-ri 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2023.09.27 a las 03:28:36 PM CEST 
//


package com.dmatob.sandbox.springboot.infrastructure.api.soap.generated;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="article" type="{http://www.dmatob.com/sandbox/springboot/infrastructure/api/soap/generated}articleSoapDTO"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "article"
})
@XmlRootElement(name = "getArticleByCodeResponse")
public class GetArticleByCodeResponse {

    @XmlElement(required = true)
    protected ArticleSoapDTO article;

    /**
     * Obtiene el valor de la propiedad article.
     * 
     * @return
     *     possible object is
     *     {@link ArticleSoapDTO }
     *     
     */
    public ArticleSoapDTO getArticle() {
        return article;
    }

    /**
     * Define el valor de la propiedad article.
     * 
     * @param value
     *     allowed object is
     *     {@link ArticleSoapDTO }
     *     
     */
    public void setArticle(ArticleSoapDTO value) {
        this.article = value;
    }

}
