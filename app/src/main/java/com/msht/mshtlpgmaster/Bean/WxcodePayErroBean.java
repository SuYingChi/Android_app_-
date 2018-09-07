package com.msht.mshtlpgmaster.Bean;

public class WxcodePayErroBean {

    /**
     * result : fail
     * error : 支付失败，当前业务系统下的订单已经支付成功，请换其他orderId
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
