package com.wk.springbootapp.app.component.es.vo;


public enum ResultVoDict {
    RESULT_OK("OK", 1), RESULT_FAIL("FAIL", 0);

    private String status;

    public String getStatus() {
        return status;
    }

    public int getStatusCode() {
        return statusCode;
    }

    private int statusCode;

    private ResultVoDict(String status, int statusCode) {
        this.status = status;
        this.statusCode = statusCode;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
