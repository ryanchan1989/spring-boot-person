package com.wk.springbootapp.app.component.es.component;

import com.wk.springbootapp.app.component.es.annotation.ESDocument;
import com.wk.springbootapp.app.component.es.bo.ESBOInfo;
import com.wk.springbootapp.app.component.es.bo.ESCriteria;
import com.wk.springbootapp.app.component.es.util.GsonUtil;
import com.wk.springbootapp.app.component.es.vo.ResultVo;
import com.wk.springbootapp.app.component.es.vo.ResultVoDict;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.rest.RestStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

public class ESRepository implements ESRepositoryProxy {

    private static final Logger logger = LoggerFactory.getLogger(ESRepository.class);

    @Autowired
    private TransportClient transportClient;

    @Override
    public <T> ResultVo<String> save(T document, Class<T> clazz) {
        String documentStr = GsonUtil.toJson(document);
        ESBOInfo indexAndType = parseDocumentIndexAndType(clazz);
        String id = fetchIdValue(document);
        IndexResponse response = transportClient.prepareIndex(indexAndType.getIndex(), indexAndType.getType(), id)
                .setSource(documentStr)
                .execute()
                .actionGet();
        if(response.status().getStatus() == RestStatus.CREATED.getStatus()){
            return returnOkWithoutData();
        }else{
            logger.error("build document failed, non 201 response {} {}",
                    response.status().getStatus(), documentStr);
        }
        return returnFailedWithoutData();
    }

    @Override
    public <T> ResultVo<String> save(List<T> documents, Class<T> clazz) {
        return null;
    }

    @Override
    public <T> ResultVo<String> deleteById(String id, Class<T> clazz) {
        return null;
    }

    @Override
    public <T> ResultVo<String> updateById(List<T> documents, Class<T> clazz) {
        return null;
    }

    @Override
    public <T> ResultVo<String> fetchByFieldValuePair(String key, String value, Class<T> clazz) {
        return null;
    }

    @Override
    public <T> ResultVo<String> fetchByFieldValuePairs(Map<String, String> map, Class<T> clazz) {
        return null;
    }

    @Override
    public <T> ResultVo<String> fetchById(String id, Class<T> clazz) {
        return null;
    }

    @Override
    public <T> ResultVo<String> fetchByCriteria(ESCriteria esCriteria, Class<T> clazz) {
        return null;
    }


    private ESBOInfo parseDocumentIndexAndType(Class clazz){
        ESBOInfo esboInfo = null;
        ESDocument esDocument = (ESDocument)clazz.getAnnotation(ESDocument.class);
        if(esDocument != null){
            esboInfo = new ESBOInfo(esDocument.index(), esDocument.type());
        }
        return esboInfo;
    }

    private <T> String fetchIdValue(T document){
        try {
            Field idField = document.getClass().getDeclaredField("id");
            idField.setAccessible(true);
            return (String)idField.get(document);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            logger.error("fetch id value failed ", e);
        }
        return "";
    }

    private <T> ResultVo<T> returnFailedWithoutData() {
        return new ResultVo<>(ResultVoDict.RESULT_FAIL.getStatusCode(),ResultVoDict.RESULT_FAIL.getStatus(), null);
    }

    private <T> ResultVo<T> returnOkWithoutData() {
        return new ResultVo<>(ResultVoDict.RESULT_OK.getStatusCode(), ResultVoDict.RESULT_OK.getStatus(), null);
    }
}
