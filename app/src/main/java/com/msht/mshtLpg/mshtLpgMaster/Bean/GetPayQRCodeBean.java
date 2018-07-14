package com.msht.mshtLpg.mshtLpgMaster.Bean;

public class GetPayQRCodeBean {

    /**
     * result :
     * msg : 创建二维码成功
     * data :
     * outTradeNo :
     */

    private String result;
    private String msg;
    private String data;
    private String outTradeNo;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }
}
