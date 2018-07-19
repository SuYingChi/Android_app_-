package com.msht.mshtLpg.mshtLpgMaster.Bean;

public class GasAndDepositBean {

    /**
     * result : success
     * msg : 获取成功
     * data : {"siteId":2,"fiveDepositFee":5.1,"fifteenDepositFee":15.1,"fiftyDepositFee":50.1,"fiveGasFee":5.2,"fifteenGasFee":15.2,"fiftyGasFee":50.2}
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
         * siteId : 2
         * fiveDepositFee : 5.1
         * fifteenDepositFee : 15.1
         * fiftyDepositFee : 50.1
         * fiveGasFee : 5.2
         * fifteenGasFee : 15.2
         * fiftyGasFee : 50.2
         */

        private int siteId;
        private double fiveDepositFee;
        private double fifteenDepositFee;
        private double fiftyDepositFee;
        private double fiveGasFee;
        private double fifteenGasFee;
        private double fiftyGasFee;

        public int getSiteId() {
            return siteId;
        }

        public void setSiteId(int siteId) {
            this.siteId = siteId;
        }

        public double getFiveDepositFee() {
            return fiveDepositFee;
        }

        public void setFiveDepositFee(double fiveDepositFee) {
            this.fiveDepositFee = fiveDepositFee;
        }

        public double getFifteenDepositFee() {
            return fifteenDepositFee;
        }

        public void setFifteenDepositFee(double fifteenDepositFee) {
            this.fifteenDepositFee = fifteenDepositFee;
        }

        public double getFiftyDepositFee() {
            return fiftyDepositFee;
        }

        public void setFiftyDepositFee(double fiftyDepositFee) {
            this.fiftyDepositFee = fiftyDepositFee;
        }

        public double getFiveGasFee() {
            return fiveGasFee;
        }

        public void setFiveGasFee(double fiveGasFee) {
            this.fiveGasFee = fiveGasFee;
        }

        public double getFifteenGasFee() {
            return fifteenGasFee;
        }

        public void setFifteenGasFee(double fifteenGasFee) {
            this.fifteenGasFee = fifteenGasFee;
        }

        public double getFiftyGasFee() {
            return fiftyGasFee;
        }

        public void setFiftyGasFee(double fiftyGasFee) {
            this.fiftyGasFee = fiftyGasFee;
        }
    }
}
