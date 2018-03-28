package com.wk.springbootapp.app.component.es.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by zhangxuan on 05/05.
 */
@Data
@AllArgsConstructor
public class ResultVo<T> {

    private int status;
    private String message;
    private T data;

    public ResultVo() {
    }

    public ResultVo(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
}
