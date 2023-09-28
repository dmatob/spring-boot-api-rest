package com.dmatob.sandbox.springboot.infrastructure.api.soap.endpoint;

import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.dmatob.sandbox.springboot.application.service.ArticleAppService;
import com.dmatob.sandbox.springboot.infrastructure.api.soap.generated.GetArticleByCodeRequest;
import com.dmatob.sandbox.springboot.infrastructure.api.soap.generated.GetArticleByCodeResponse;
import com.dmatob.sandbox.springboot.infrastructure.api.soap.mapper.ArticleSoapDTOMapper;

@Endpoint
public class ArticleSoapEndpoint {
    
    private static final String NAMESPACE_URI = "http://www.dmatob.com/sandbox/springboot/infrastructure/api/soap/generated";
    private static final Logger LOG = LoggerFactory.getLogger(ArticleSoapEndpoint.class);

    private final ArticleSoapDTOMapper mapper = Mappers.getMapper(ArticleSoapDTOMapper.class);
    private final ArticleAppService articleAppService;

    public ArticleSoapEndpoint(ArticleAppService articleService) {
        this.articleAppService = articleService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getArticleByCodeRequest")
    @ResponsePayload
    public GetArticleByCodeResponse getArticle(@RequestPayload GetArticleByCodeRequest request) {
        LOG.info("Llamada a la API-SOAP de articulos para obtener la información de un artículo");
        GetArticleByCodeResponse response = new GetArticleByCodeResponse();
        response.setArticle(this.mapper.toArticleDTO(this.articleAppService.getArticle(request.getCode())));
        return response;
    }
}
