
package com.dmatob.sandbox.springboot.infrastructure.api.soap.generated;

import jakarta.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.dmatob.sandbox.springboot.infrastructure.api.soap.generated package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.dmatob.sandbox.springboot.infrastructure.api.soap.generated
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetArticleByCodeRequest }
     * 
     */
    public GetArticleByCodeRequest createGetArticleByCodeRequest() {
        return new GetArticleByCodeRequest();
    }

    /**
     * Create an instance of {@link GetArticleByCodeResponse }
     * 
     */
    public GetArticleByCodeResponse createGetArticleByCodeResponse() {
        return new GetArticleByCodeResponse();
    }

    /**
     * Create an instance of {@link ArticleSoapDTO }
     * 
     */
    public ArticleSoapDTO createArticleSoapDTO() {
        return new ArticleSoapDTO();
    }

    /**
     * Create an instance of {@link ArticleTypeSoapDTO }
     * 
     */
    public ArticleTypeSoapDTO createArticleTypeSoapDTO() {
        return new ArticleTypeSoapDTO();
    }

}
