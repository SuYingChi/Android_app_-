package com.msht.mshtLpg.mshtLpgMaster.Bean;

public class QueryOrderBean {

    /**
     * result : success
     * msg : 查询订单成功
     * data : {"addressName":"海口市白龙北路5号","floor":5,"siteName":"海师站2","buyer":"李四","sex":"1","mobile":"18976991794","retrieveAmount":0,"deliveryTime":"尽快送达","realAmount":900,"orderStatus":3,"fiveBottleCount":1,"fifteenBottleCount":0,"fiftyBottleCount":0,"depositAmount":0,"deliveryAmount":8,"orderId":113,"createDate":"2018-07-11 14:43:43"}
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
         * addressName : 海口市白龙北路5号
         * floor : 5
         * siteName : 海师站2
         * buyer : 李四
         * sex : 1
         * mobile : 18976991794
         * retrieveAmount : 0
         * deliveryTime : 尽快送达
         * realAmount : 900
         * orderStatus : 3
         * fiveBottleCount : 1
         * fifteenBottleCount : 0
         * fiftyBottleCount : 0
         * depositAmount : 0
         * deliveryAmount : 8
         * orderId : 113
         * createDate : 2018-07-11 14:43:43
         */

        private String addressName;
        private int floor;
        private String siteName;
        private String buyer;
        private String sex;
        private String mobile;
        private int retrieveAmount;
        private String deliveryTime;
        private  Double realAmount;
        private int orderStatus;
        private int fiveBottleCount;
        private int fifteenBottleCount;
        private int fiftyBottleCount;
        private Double depositAmount;
        private Double deliveryAmount;
        private int orderId;
        private String createDate;

        public String getAddressName() {
            return addressName;
        }

        public void setAddressName(String addressName) {
            this.addressName = addressName;
        }

        public int getFloor() {
            return floor;
        }

        public void setFloor(int floor) {
            this.floor = floor;
        }

        public String getSiteName() {
            return siteName;
        }

        public void setSiteName(String siteName) {
            this.siteName = siteName;
        }

        public String getBuyer() {
            return buyer;
        }

        public void setBuyer(String buyer) {
            this.buyer = buyer;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public int getRetrieveAmount() {
            return retrieveAmount;
        }

        public void setRetrieveAmount(int retrieveAmount) {
            this.retrieveAmount = retrieveAmount;
        }

        public String getDeliveryTime() {
            return deliveryTime;
        }

        public void setDeliveryTime(String deliveryTime) {
            this.deliveryTime = deliveryTime;
        }

        public Double getRealAmount() {
            return realAmount;
        }

        public void setRealAmount(Double realAmount) {
            this.realAmount = realAmount;
        }

        public int getOrderStatus() {
            return orderStatus;
        }

        public void setOrderStatus(int orderStatus) {
            this.orderStatus = orderStatus;
        }

        public int getFiveBottleCount() {
            return fiveBottleCount;
        }

        public void setFiveBottleCount(int fiveBottleCount) {
            this.fiveBottleCount = fiveBottleCount;
        }

        public int getFifteenBottleCount() {
            return fifteenBottleCount;
        }

        public void setFifteenBottleCount(int fifteenBottleCount) {
            this.fifteenBottleCount = fifteenBottleCount;
        }

        public int getFiftyBottleCount() {
            return fiftyBottleCount;
        }

        public void setFiftyBottleCount(int fiftyBottleCount) {
            this.fiftyBottleCount = fiftyBottleCount;
        }

        public Double getDepositAmount() {
            return depositAmount;
        }

        public void setDepositAmount(Double depositAmount) {
            this.depositAmount = depositAmount;
        }

        public Double getDeliveryAmount() {
            return deliveryAmount;
        }

        public void setDeliveryAmount(Double deliveryAmount) {
            this.deliveryAmount = deliveryAmount;
        }

        public int getOrderId() {
            return orderId;
        }

        public void setOrderId(int orderId) {
            this.orderId = orderId;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }
    }
}
