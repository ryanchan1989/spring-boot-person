package com.wk.springbootapp.app.component.es.util;

import com.google.gson.Gson;

/**
 * Created by kavin on 2017/8/18.
 */
public class GsonUtil {

    public  static <T> String toJson(T obj) {
        Gson gson = new Gson();
        return gson.toJson(obj);
    }

    public static <T> T parseJson(String str, Class<T> clazz) {
        Gson gson = new Gson();
        return gson.fromJson(str, clazz);
    }
}
