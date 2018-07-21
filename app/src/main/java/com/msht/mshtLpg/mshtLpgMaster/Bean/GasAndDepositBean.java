package com.msht.mshtLpg.mshtLpgMaster.Bean;

public class GasAndDepositBean {

    /**
     * result : success
     * msg : 获取成功
     * data : {"depositPrice":{"id":1,"ids":"","fiveBottleWeight":5,"fiveDepositPrice":5,"fifteenBottleWeight":15,"fifteenDepositPrice":15,"fiftyBottleWeight":50,"fiftyDepositPrice":50},"gasPrice":{"id":5,"ids":"","siteId":2,"fivePrice":5.2,"fifteenPrice":15.2,"thirtyPrice":"","fiftyPrice":50.2,"validDate":"","remarks":""}}
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
         * depositPrice : {"id":1,"ids":"","fiveBottleWeight":5,"fiveDepositPrice":5,"fifteenBottleWeight":15,"fifteenDepositPrice":15,"fiftyBottleWeight":50,"fiftyDepositPrice":50}
         * gasPrice : {"id":5,"ids":"","siteId":2,"fivePrice":5.2,"fifteenPrice":15.2,"thirtyPrice":"","fiftyPrice":50.2,"validDate":"","remarks":""}
         */

        private DepositPriceBean depositPrice;
        private GasPriceBean gasPrice;

        public DepositPriceBean getDepositPrice() {
            return depositPrice;
        }

        public void setDepositPrice(DepositPriceBean depositPrice) {
            this.depositPrice = depositPrice;
        }

        public GasPriceBean getGasPrice() {
            return gasPrice;
        }

        public void setGasPrice(GasPriceBean gasPrice) {
            this.gasPrice = gasPrice;
        }

        public static class DepositPriceBean {
            /**
             * id : 1
             * ids :
             * fiveBottleWeight : 5
             * fiveDepositPrice : 5
             * fifteenBottleWeight : 15
             * fifteenDepositPrice : 15
             * fiftyBottleWeight : 50
             * fiftyDepositPrice : 50
             */

            private int id;
            private String ids;
            private int fiveBottleWeight;
            private int fiveDepositPrice;
            private int fifteenBottleWeight;
            private int fifteenDepositPrice;
            private int fiftyBottleWeight;
            private int fiftyDepositPrice;

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

            public int getFiveBottleWeight() {
                return fiveBottleWeight;
            }

            public void setFiveBottleWeight(int fiveBottleWeight) {
                this.fiveBottleWeight = fiveBottleWeight;
            }

            public int getFiveDepositPrice() {
                return fiveDepositPrice;
            }

            public void setFiveDepositPrice(int fiveDepositPrice) {
                this.fiveDepositPrice = fiveDepositPrice;
            }

            public int getFifteenBottleWeight() {
                return fifteenBottleWeight;
            }

            public void setFifteenBottleWeight(int fifteenBottleWeight) {
                this.fifteenBottleWeight = fifteenBottleWeight;
            }

            public int getFifteenDepositPrice() {
                return fifteenDepositPrice;
            }

            public void setFifteenDepositPrice(int fifteenDepositPrice) {
                this.fifteenDepositPrice = fifteenDepositPrice;
            }

            public int getFiftyBottleWeight() {
                return fiftyBottleWeight;
            }

            public void setFiftyBottleWeight(int fiftyBottleWeight) {
                this.fiftyBottleWeight = fiftyBottleWeight;
            }

            public int getFiftyDepositPrice() {
                return fiftyDepositPrice;
            }

            public void setFiftyDepositPrice(int fiftyDepositPrice) {
                this.fiftyDepositPrice = fiftyDepositPrice;
            }
        }

        public static class GasPriceBean {
            /**
             * id : 5
             * ids :
             * siteId : 2
             * fivePrice : 5.2
             * fifteenPrice : 15.2
             * thirtyPrice :
             * fiftyPrice : 50.2
             * validDate :
             * remarks :
             */

            private int id;
            private String ids;
            private int siteId;
            private double fivePrice;
            private double fifteenPrice;
            private String thirtyPrice;
            private double fiftyPrice;
            private String validDate;
            private String remarks;

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

            public int getSiteId() {
                return siteId;
            }

            public void setSiteId(int siteId) {
                this.siteId = siteId;
            }

            public double getFivePrice() {
                return fivePrice;
            }

            public void setFivePrice(double fivePrice) {
                this.fivePrice = fivePrice;
            }

            public double getFifteenPrice() {
                return fifteenPrice;
            }

            public void setFifteenPrice(double fifteenPrice) {
                this.fifteenPrice = fifteenPrice;
            }

            public String getThirtyPrice() {
                return thirtyPrice;
            }

            public void setThirtyPrice(String thirtyPrice) {
                this.thirtyPrice = thirtyPrice;
            }

            public double getFiftyPrice() {
                return fiftyPrice;
            }

            public void setFiftyPrice(double fiftyPrice) {
                this.fiftyPrice = fiftyPrice;
            }

            public String getValidDate() {
                return validDate;
            }

            public void setValidDate(String validDate) {
                this.validDate = validDate;
            }

            public String getRemarks() {
                return remarks;
            }

            public void setRemarks(String remarks) {
                this.remarks = remarks;
            }
        }
    }
}
