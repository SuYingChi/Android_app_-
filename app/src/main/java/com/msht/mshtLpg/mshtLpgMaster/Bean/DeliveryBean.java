package com.msht.mshtLpg.mshtLpgMaster.Bean;

public class DeliveryBean {

    /**
     * result : success
     * msg : 获取运费成功
     * data : {"deliveryFee":{"fiveDeliveryFee":1,"fifteenDeliveryFee":3,"fiftyDeliveryFee":10}}
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
         * deliveryFee : {"fiveDeliveryFee":1,"fifteenDeliveryFee":3,"fiftyDeliveryFee":10}
         */

        private DeliveryFeeBean deliveryFee;

        public DeliveryFeeBean getDeliveryFee() {
            return deliveryFee;
        }

        public void setDeliveryFee(DeliveryFeeBean deliveryFee) {
            this.deliveryFee = deliveryFee;
        }

        public static class DeliveryFeeBean {
            /**
             * fiveDeliveryFee : 1
             * fifteenDeliveryFee : 3
             * fiftyDeliveryFee : 10
             */

            private int fiveDeliveryFee;
            private int fifteenDeliveryFee;
            private int fiftyDeliveryFee;

            public int getFiveDeliveryFee() {
                return fiveDeliveryFee;
            }

            public void setFiveDeliveryFee(int fiveDeliveryFee) {
                this.fiveDeliveryFee = fiveDeliveryFee;
            }

            public int getFifteenDeliveryFee() {
                return fifteenDeliveryFee;
            }

            public void setFifteenDeliveryFee(int fifteenDeliveryFee) {
                this.fifteenDeliveryFee = fifteenDeliveryFee;
            }

            public int getFiftyDeliveryFee() {
                return fiftyDeliveryFee;
            }

            public void setFiftyDeliveryFee(int fiftyDeliveryFee) {
                this.fiftyDeliveryFee = fiftyDeliveryFee;
            }
        }
    }
}
