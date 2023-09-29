package com.dmatob.sandbox.springboot.infrastructure.api.soap;

import static org.springframework.ws.test.server.RequestCreators.withPayload;
import static org.springframework.ws.test.server.ResponseMatchers.noFault;
import static org.springframework.ws.test.server.ResponseMatchers.xpath;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.webservices.server.WebServiceServerTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.ws.test.server.MockWebServiceClient;
import org.springframework.xml.transform.StringSource;

import com.dmatob.sandbox.springboot.application.service.ArticleAppService;
import com.dmatob.sandbox.springboot.domain.model.Article;
import com.dmatob.sandbox.springboot.domain.model.ArticleProvider;


@WebServiceServerTest
public class ArticleSoapEndpointTest {
    
    private static final Map<String, String> NAMESPACE_MAPPING = Map.ofEntries(
            Map.entry("tns", "http://www.dmatob.com/sandbox/springboot/infrastructure/api/soap/generated")
    );

    @Autowired
    private MockWebServiceClient client;

    @MockBean
    private ArticleAppService articleService;

    @Test
    void shouldGetArticle_returnData(){

        Article article = ArticleProvider.getArticle();
	    Mockito.when(articleService.getArticle(article.getCode())).thenReturn(article);

        final var request = "<tns:getArticleByCodeRequest xmlns:tns=\"http://www.dmatob.com/sandbox/springboot/infrastructure/api/soap/generated\">\n" +
                            "   <tns:code>"+ article.getCode() +"</tns:code>\n" + 
                            "</tns:getArticleByCodeRequest>";

        this.client
                .sendRequest(withPayload(new StringSource(request)))
                .andExpect(noFault())
                .andExpect(xpath("/tns:getArticleByCodeResponse/tns:article/tns:code", NAMESPACE_MAPPING).evaluatesTo(article.getCode()))
                .andExpect(xpath("/tns:getArticleByCodeResponse/tns:article/tns:id", NAMESPACE_MAPPING).evaluatesTo(article.getId()))
                .andExpect(xpath("/tns:getArticleByCodeResponse/tns:article/tns:description", NAMESPACE_MAPPING).evaluatesTo(article.getDescription()));
    }

    @Test
    void shouldGetArticle_returnVoid(){

        Article article = ArticleProvider.getArticle();
	    Mockito.when(articleService.getArticle(article.getCode())).thenReturn(null);

        final var request = "<tns:getArticleByCodeRequest xmlns:tns=\"http://www.dmatob.com/sandbox/springboot/infrastructure/api/soap/generated\">\n" +
                            "   <tns:code>"+ article.getCode() +"</tns:code>\n" + 
                            "</tns:getArticleByCodeRequest>";

        this.client
                .sendRequest(withPayload(new StringSource(request)))
                .andExpect(noFault())
                .andExpect(xpath("/tns:getArticleByCodeResponse/tns:article", NAMESPACE_MAPPING).doesNotExist());
    }
}
