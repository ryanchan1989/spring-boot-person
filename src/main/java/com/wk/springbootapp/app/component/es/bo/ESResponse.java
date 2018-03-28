package com.wk.springbootapp.app.component.es.bo;

import lombok.Data;

import java.util.List;

@Data
public class ESResponse<T> {
    private Long total;

    private Integer page;

    private List<T> content;

    public ESResponse(){}

    public ESResponse(List<T> content){
        this.content = content;
    }
}
