package com.msht.mshtlpgmaster.Bean;

import java.util.List;

public class ExchangeReviewBean {

    /**
     * result : success
     * msg : 查询成功
     * data : [{"id":1,"ids":"","orderId":165,"bottleWeight":15,"bottleCount":1,"replacePrice":105,"totalAmount":0,"bottleYear":"","years":1,"corrosionType":"充新"},{"id":2,"ids":"","orderId":165,"bottleWeight":15,"bottleCount":1,"replacePrice":105,"totalAmount":0,"bottleYear":"","years":1,"corrosionType":"轻微"}]
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
         * ids :
         * orderId : 165
         * bottleWeight : 15
         * bottleCount : 1
         * replacePrice : 105
         * totalAmount : 0
         * bottleYear :
         * years : 1
         * corrosionType : 充新
         */

        private int id;
        private String ids;
        private int orderId;
        private int bottleWeight;
        private int bottleCount;
        private int replacePrice;
        private int totalAmount;
        private String bottleYear;
        private int years;
        private String corrosionType;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getIds() {
            return ids;
        }

        public void setIds(String ids) {
            this.ids = ids;
        }

        public int getOrderId() {
            return orderId;
        }

        public void setOrderId(int orderId) {
            this.orderId = orderId;
        }

        public int getBottleWeight() {
            return bottleWeight;
        }

        public void setBottleWeight(int bottleWeight) {
            this.bottleWeight = bottleWeight;
        }

        public int getBottleCount() {
            return bottleCount;
        }

        public void setBottleCount(int bottleCount) {
            this.bottleCount = bottleCount;
        }

        public int getReplacePrice() {
            return replacePrice;
        }

        public void setReplacePrice(int replacePrice) {
            this.replacePrice = replacePrice;
        }

        public int getTotalAmount() {
            return totalAmount;
        }

        public void setTotalAmount(int totalAmount) {
            this.totalAmount = totalAmount;
        }

        public String getBottleYear() {
            return bottleYear;
        }

        public void setBottleYear(String bottleYear) {
            this.bottleYear = bottleYear;
        }

        public int getYears() {
            return years;
        }

        public void setYears(int years) {
            this.years = years;
        }

        public String getCorrosionType() {
            return corrosionType;
        }

        public void setCorrosionType(String corrosionType) {
            this.corrosionType = corrosionType;
        }
    }
}
