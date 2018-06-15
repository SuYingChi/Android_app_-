package com.msht.mshtLpg.mshtLpgMaster.netBean;

public class ErrorBean {

    /**
     * result : fail
     * error : 验证码已经过期(有效期两分钟)，请重新获取
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
