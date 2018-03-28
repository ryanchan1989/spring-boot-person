package com.wk.springbootapp.app.component.es.util;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import java.util.Map;

/**
 * Created by kavin on 2017/8/23.
 */
public class ESQueryUtil {


    /**
     * 仅匹配一个
     * 如果value值以:分割，表示区间，下划线开头，则表示闭区间
     * @param key
     * @param value
     * @return
     */
    public static QueryBuilder match(String key, String value) {
        if (value.contains(":")) {
            String[] rangeValues = value.split(":");
            Boolean isIncludeLower = rangeValues[0].substring(0,1).equals("_");
            Boolean isIncludeUpper = rangeValues[1].substring(0,1).equals("_");
            String lowCondition = rangeValues[0];
            String upperCondition = rangeValues[1];
            if (isIncludeLower) {
                lowCondition = rangeValues[0].substring(1);
            }
            if (isIncludeUpper) {
                upperCondition = rangeValues[1].substring(1);
            }
            QueryBuilder qb = QueryBuilders.rangeQuery(key)
                .from(lowCondition)
                .to(upperCondition)
                .includeLower(isIncludeLower)
                .includeUpper(isIncludeUpper);
            return qb;
        } else {
            return QueryBuilders.matchQuery(key, value);
        }
    }

    public static QueryBuilder match(Map<String,String> params) {
        BoolQueryBuilder qb = QueryBuilders
                .boolQuery();
        for (String key: params.keySet()) {
            String value = params.get(key);
            qb.must(match(key, value));
        }
        return qb;
    }
}
