package com.wk.springbootapp.app.component.es.bo;

import java.util.Map;

public interface ESBO {

    void buildFromESSource(Map<String, Object> source) throws Exception;
}
