package es.sprinter.technicaltest.application.rest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import es.sprinter.technicaltest.domain.exception.ArticleNotFoundException;
import es.sprinter.technicaltest.domain.exception.DuplicatedArticleException;
import es.sprinter.technicaltest.domain.exception.InvalidArticlePriceException;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { ArticleNotFoundException.class })
    protected ResponseEntity<Object> articleNotFound ( RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "El artículo especificado no existe";
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
    
    @ExceptionHandler(value = { InvalidArticlePriceException.class })
    protected ResponseEntity<Object> invalidArticlePrice ( RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "El precio del artículo es incorrecto. El precio ha de ser mayor a 0€";
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
    
    @ExceptionHandler(value = { DuplicatedArticleException.class })
    protected ResponseEntity<Object> duplicatedArtcile ( RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "El articulo ya existe";
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}