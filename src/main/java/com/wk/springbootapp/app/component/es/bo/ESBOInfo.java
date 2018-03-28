package com.wk.springbootapp.app.component.es.bo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by kavin on 2017/8/18.
 */
@Data
@AllArgsConstructor
public class ESBOInfo {

    private String index;
    private String type;

    public ESBOInfo(String index, String type) {
        this.index = index;
        this.type = type;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
