package com.wk.springbootapp.app.component.es.bo;

import lombok.Data;
import lombok.NonNull;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by kavin on 2017/9/11.
 */
@Data
@ToString
public class ESCriteria {

    private Map<String, String> criteriaContainer;

    private Integer page;

    private Integer limit;

    private Map<String, String> sort;

    public ESCriteria() {
        criteriaContainer = new HashMap<>();
        sort = new HashMap<>();
    }

    public ESCriteria addCriteria(@NonNull String key, @NonNull String value) {
        criteriaContainer.put(key, value);
        return this;
    }

    public ESCriteria addSort(@NonNull String key, @NonNull String sortOrder) {
        sort.put(key, sortOrder);
        return this;
    }

    public Boolean isEmpty() {
        if (Objects.equals(this.criteriaContainer.keySet().size() , 0)) {
            return true;
        }
        return false;
    }
}
