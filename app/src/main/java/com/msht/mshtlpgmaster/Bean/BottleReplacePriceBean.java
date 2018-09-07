package com.msht.mshtlpgmaster.Bean;

public class BottleReplacePriceBean {
    /**
     * result : success
     * msg : 获取钢瓶置换价格成功
     * data : {"id":2,"ids":"","bottleWeight":15,"bottlePrice":105,"bottleProduceDate":"","years":1,"corrosionType":"B"}
     */

    private String result;
    private String msg;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 2
         * ids :
         * bottleWeight : 15
         * bottlePrice : 105
         * bottleProduceDate :
         * years : 1
         * corrosionType : B
         */

        private int id;
        private String ids;
        private int bottleWeight;
        private int bottlePrice;
        private String bottleProduceDate;
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
