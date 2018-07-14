package com.msht.mshtLpg.mshtLpgMaster.Bean;

import java.util.List;

public class SubstitutionListBean {

    /**
     * result : success
     * msg : 获取钢瓶折价信息成功
     * data : [{"id":1,"bottleWeight":15,"bottlePrice":15,"bottleProduceDate":"2018"},{"id":2,"bottleWeight":15,"bottlePrice":15.1,"bottleProduceDate":"2017"},{"id":3,"bottleWeight":5,"bottlePrice":5,"bottleProduceDate":"2018"},{"id":5,"bottleWeight":50,"bottlePrice":50,"bottleProduceDate":"2018"}]
     */

    private String result;
    private String msg;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * bottleWeight : 15
         * bottlePrice : 15
         * bottleProduceDate : 2018
         */

        private int id;
        private int bottleWeight;
        private int bottlePrice;
        private String bottleProduceDate;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getBottleWeight() {
            return bottleWeight;
        }

        public void setBottleWeight(int bottleWeight) {
            this.bottleWeight = bottleWeight;
        }

        public int getBottlePrice() {
            return bottlePrice;
        }

        public void setBottlePrice(int bottlePrice) {
            this.bottlePrice = bottlePrice;
        }

        public String getBottleProduceDate() {
            return bottleProduceDate;
        }

        public void setBottleProduceDate(String bottleProduceDate) {
            this.bottleProduceDate = bottleProduceDate;
        }
    }
}
