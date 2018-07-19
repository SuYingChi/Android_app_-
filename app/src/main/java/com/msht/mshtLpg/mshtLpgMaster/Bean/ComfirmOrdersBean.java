package com.msht.mshtLpg.mshtLpgMaster.Bean;

import java.io.Serializable;

public class ComfirmOrdersBean implements Serializable{

    /**
     * result : success
     * msg : 订单信息确认成功
     * data : {"userName":"刘备","sex":1,"address":"海口市建军32号","floor":20,"remarks":"","deliveryTime":"尽快配送","createTime":"","sendTime":"","orderId":111,"reFiveBottleCount":0,"reFifteenBottleCount":0,"reFiftyBottleCount":0,"fiveBottleCount":1,"fifteenBottleCount":0,"fiftyBottleCount":0,"fiveDepositFee":5.1,"fifteenDepositFee":15.1,"fiftyDepositFee":50.1,"fiveDeliveryFee":2.7,"fifteenDeliveryFee":8,"fiftyDeliveryFee":10}
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

    public static class DataBean implements Serializable{
        /**
         * userName : 刘备
         * sex : 1
         * address : 海口市建军32号
         * floor : 20
         * remarks :
         * deliveryTime : 尽快配送
         * createTime :
         * sendTime :
         * orderId : 111
         * reFiveBottleCount : 0
         * reFifteenBottleCount : 0
         * reFiftyBottleCount : 0
         * fiveBottleCount : 1
         * fifteenBottleCount : 0
         * fiftyBottleCount : 0
         * fiveDepositFee : 5.1
         * fifteenDepositFee : 15.1
         * fiftyDepositFee : 50.1
         * fiveDeliveryFee : 2.7
         * fifteenDeliveryFee : 8
         * fiftyDeliveryFee : 10
         */

        private String userName;
        private int sex;
        private String address;
        private int floor;
        private String remarks;
        private String deliveryTime;
        private String createTime;
        private String sendTime;
        private int orderId;
        private int reFiveBottleCount;
        private int reFifteenBottleCount;
        private int reFiftyBottleCount;
        private int fiveBottleCount;
        private int fifteenBottleCount;
        private int fiftyBottleCount;
        private double fiveDepositFee;
        private double fifteenDepositFee;
        private double fiftyDepositFee;
        private double fiveDeliveryFee;
        private int fifteenDeliveryFee;
        private int fiftyDeliveryFee;

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getFloor() {
            return floor;
        }

        public void setFloor(int floor) {
            this.floor = floor;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public String getDeliveryTime() {
            return deliveryTime;
        }

        public void setDeliveryTime(String deliveryTime) {
            this.deliveryTime = deliveryTime;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getSendTime() {
            return sendTime;
        }

        public void setSendTime(String sendTime) {
            this.sendTime = sendTime;
        }

        public int getOrderId() {
            return orderId;
        }

        public void setOrderId(int orderId) {
            this.orderId = orderId;
        }

        public int getReFiveBottleCount() {
            return reFiveBottleCount;
        }

        public void setReFiveBottleCount(int reFiveBottleCount) {
            this.reFiveBottleCount = reFiveBottleCount;
        }

        public int getReFifteenBottleCount() {
            return reFifteenBottleCount;
        }

        public void setReFifteenBottleCount(int reFifteenBottleCount) {
            this.reFifteenBottleCount = reFifteenBottleCount;
        }

        public int getReFiftyBottleCount() {
            return reFiftyBottleCount;
        }

        public void setReFiftyBottleCount(int reFiftyBottleCount) {
            this.reFiftyBottleCount = reFiftyBottleCount;
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

        public double getFiveDeliveryFee() {
            return fiveDeliveryFee;
        }

        public void setFiveDeliveryFee(double fiveDeliveryFee) {
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
