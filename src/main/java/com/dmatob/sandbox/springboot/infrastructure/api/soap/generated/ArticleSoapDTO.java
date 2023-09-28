
package com.dmatob.sandbox.springboot.infrastructure.api.soap.generated;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.dmatob.sandbox.springboot.infrastructure.api.soap.adapter.LocalDateTimeAdapter;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Clase Java para articleSoapDTO complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="articleSoapDTO"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="type" type="{http://www.dmatob.com/sandbox/springboot/infrastructure/api/soap/generated}articleTypeSoapDTO"/&gt;
 *         &lt;element name="code" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="price" type="{http://www.w3.org/2001/XMLSchema}decimal"/&gt;
 *         &lt;element name="lastModificationDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "articleSoapDTO", propOrder = {
    "id",
    "type",
    "code",
    "description",
    "price",
    "lastModificationDate"
})
public class ArticleSoapDTO {

    protected long id;
    @XmlElement(required = true)
    protected ArticleTypeSoapDTO type;
    @XmlElement(required = true)
    protected String code;
    @XmlElement(required = true)
    protected String description;
    @XmlElement(required = true)
    protected BigDecimal price;
    @XmlElement(required = true, type = String.class)
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    @XmlSchemaType(name = "dateTime")
    protected LocalDateTime lastModificationDate;

    /**
     * Obtiene el valor de la propiedad id.
     * 
     */
    public long getId() {
        return id;
    }

    /**
     * Define el valor de la propiedad id.
     * 
     */
    public void setId(long value) {
        this.id = value;
    }

    /**
     * Obtiene el valor de la propiedad type.
     * 
     * @return
     *     possible object is
     *     {@link ArticleTypeSoapDTO }
     *     
     */
    public ArticleTypeSoapDTO getType() {
        return type;
    }

    /**
     * Define el valor de la propiedad type.
     * 
     * @param value
     *     allowed object is
     *     {@link ArticleTypeSoapDTO }
     *     
     */
    public void setType(ArticleTypeSoapDTO value) {
        this.type = value;
    }

    /**
     * Obtiene el valor de la propiedad code.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCode() {
        return code;
    }

    /**
     * Define el valor de la propiedad code.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCode(String value) {
        this.code = value;
    }

    /**
     * Obtiene el valor de la propiedad description.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Define el valor de la propiedad description.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Obtiene el valor de la propiedad price.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Define el valor de la propiedad price.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPrice(BigDecimal value) {
        this.price = value;
    }

    /**
     * Obtiene el valor de la propiedad lastModificationDate.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public LocalDateTime getLastModificationDate() {
        return lastModificationDate;
    }

    /**
     * Define el valor de la propiedad lastModificationDate.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastModificationDate(LocalDateTime value) {
        this.lastModificationDate = value;
    }

}
