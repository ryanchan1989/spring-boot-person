package com.wk.springbootapp.app.component.es.bo;

import com.wk.springbootapp.app.component.es.annotation.ESDocument;
import com.wk.springbootapp.app.component.es.annotation.ESId;
import lombok.Data;

import java.lang.reflect.Field;
import java.util.Map;
@Data
@ESDocument(index = "settle", type = "settleorder")
public class ESSettleOrderBo implements ESBO {

    @ESId
    private String id;

    private Long settleOrderId;

    @Override
    public void buildFromESSource(Map<String, Object> source) throws Exception {
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field: fields) {
            if (source.containsKey(field.getName())) {
                if (field.getType().getName().equals("java.lang.Long")){
                    field.set(this, Long.valueOf(String.valueOf(source.get(field.getName()))));
                } else {
                    field.set(this, source.get(field.getName()));
                }
            }
        }

    }
}
