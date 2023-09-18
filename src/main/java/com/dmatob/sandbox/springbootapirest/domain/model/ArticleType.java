package com.dmatob.sandbox.springbootapirest.domain.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Builder
@EqualsAndHashCode
public class ArticleType {
    
    private Long id;
    private String code;
    private String description;

    public boolean isValid(){
        return id != null && code != null;
    }
    
}
