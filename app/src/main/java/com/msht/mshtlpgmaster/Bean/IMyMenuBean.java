package com.msht.mshtlpgmaster.Bean;

import java.util.List;

public class IMyMenuBean {

    /**
     * result : success
     * msg : 成功
     * data : ["我的钢瓶","调拨出库","调拨入库","钢瓶注册","领瓶出库","返瓶入库"]
     */

    private String result;
    private String msg;
    private List<String> data;

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

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
