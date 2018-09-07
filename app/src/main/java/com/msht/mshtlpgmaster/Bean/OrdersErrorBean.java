package com.msht.mshtlpgmaster.Bean;

public class OrdersErrorBean {

    /**
     * result : fail
     * error : 未登录
     */

    private String result;
    private String error;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
