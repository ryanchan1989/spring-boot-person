package com.wk.springbootapp.app.component.es.component;

import com.wk.springbootapp.app.component.es.bo.ESCriteria;
import com.wk.springbootapp.app.component.es.vo.ResultVo;

import java.util.List;
import java.util.Map;

public interface ESRepositoryProxy {

    <T> ResultVo<String> save(T document, Class<T> clazz);

    <T> ResultVo<String> save(List<T> documents, Class<T> clazz);

    <T> ResultVo<String> deleteById(String id,Class<T> clazz);

    <T> ResultVo<String> updateById(List<T> documents,Class<T> clazz);

    <T> ResultVo<String> fetchByFieldValuePair(String key,String value, Class<T> clazz);

    <T> ResultVo<String> fetchByFieldValuePairs(Map<String, String> map ,Class<T> clazz);

    <T> ResultVo<String> fetchById(String id,Class<T> clazz);

    <T> ResultVo<String> fetchByCriteria(ESCriteria esCriteria, Class<T> clazz);
}
