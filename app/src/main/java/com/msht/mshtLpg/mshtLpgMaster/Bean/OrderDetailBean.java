package com.msht.mshtLpg.mshtLpgMaster.Bean;


public class OrderDetailBean  {

    /**
     * result : success
     * msg : 查询成功
     * data : {"siteId":2,"orderId":165,"orderType":1,"orderStatus":2,"orderSource":1,"isDelivery":0,"appointmentTime":"","createDate":"","remarks":"","buyer":"黄水亮","sex":1,"mobile":"13098966725","orderAddressId":180,"longitude":"110.30204","latitude":"20.020434","addressShort":"","address":"海南省海口市龙华区金贸西路8号诚田国际商务大厦","floor":1,"roomNum":"","isElevator":0,"fiveBottleCount":1,"fifteenBottleCount":1,"fiftyBottleCount":0,"fiveGasFee":0,"fifteenGasFee":0,"fiftyGasFee":0,"fiveDeliveryFee":5,"fifteenDeliveryFee":15,"fiftyDeliveryFee":50,"fiveDepositFee":5,"fifteenDepositFee":15,"fiftyDepositFee":50,"retrieveAmount":0,"realAmount":40.4}
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
         * orderId : 165
         * orderType : 1
         * orderStatus : 2
         * orderSource : 1
         * isDelivery : 0
         * appointmentTime :
         * createDate :
         * remarks :
         * buyer : 黄水亮
         * sex : 1
         * mobile : 13098966725
         * orderAddressId : 180
         * longitude : 110.30204
         * latitude : 20.020434
         * addressShort :
         * address : 海南省海口市龙华区金贸西路8号诚田国际商务大厦
         * floor : 1
         * roomNum :
         * isElevator : 0
         * fiveBottleCount : 1
         * fifteenBottleCount : 1
         * fiftyBottleCount : 0
         * fiveGasFee : 0
         * fifteenGasFee : 0
         * fiftyGasFee : 0
         * fiveDeliveryFee : 5
         * fifteenDeliveryFee : 15
         * fiftyDeliveryFee : 50
         * fiveDepositFee : 5
         * fifteenDepositFee : 15
         * fiftyDepositFee : 50
         * retrieveAmount : 0
         * realAmount : 40.4
         */

        private int siteId;
        private int orderId;
        private int orderType;
        private int orderStatus;
        private int orderSource;
        private int isDelivery;
        private String appointmentTime;
        private String createDate;
        private String remarks;
        private String buyer;
        private int sex;
        private String mobile;
        private int orderAddressId;
        private String longitude;
        private String latitude;
        private String addressShort;
        private String address;
        private int floor;
        private String roomNum;
        private int isElevator;
        private int fiveBottleCount;
        private int fifteenBottleCount;
        private int fiftyBottleCount;
        private int fiveGasFee;
        private int fifteenGasFee;
        private int fiftyGasFee;
        private int fiveDeliveryFee;
        private int fifteenDeliveryFee;
        private int fiftyDeliveryFee;
        private int fiveDepositFee;
        private int fifteenDepositFee;
        private int fiftyDepositFee;
        private int retrieveAmount;
        private double realAmount;

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

        private int reFiveBottleCount;
        private int reFifteenBottleCount;
        private int reFiftyBottleCount;

        public int getSiteId() {
            return siteId;
        }

        public void setSiteId(int siteId) {
            this.siteId = siteId;
        }

        public int getOrderId() {
            return orderId;
        }

        public void setOrderId(int orderId) {
            this.orderId = orderId;
        }

        public int getOrderType() {
            return orderType;
        }

        public void setOrderType(int orderType) {
            this.orderType = orderType;
        }

        public int getOrderStatus() {
            return orderStatus;
        }

        public void setOrderStatus(int orderStatus) {
            this.orderStatus = orderStatus;
        }

        public int getOrderSource() {
            return orderSource;
        }

        public void setOrderSource(int orderSource) {
            this.orderSource = orderSource;
        }

        public int getIsDelivery() {
            return isDelivery;
        }

        public void setIsDelivery(int isDelivery) {
            this.isDelivery = isDelivery;
        }

        public String getAppointmentTime() {
            return appointmentTime;
        }

        public void setAppointmentTime(String appointmentTime) {
            this.appointmentTime = appointmentTime;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public String getBuyer() {
            return buyer;
        }

        public void setBuyer(String buyer) {
            this.buyer = buyer;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public int getOrderAddressId() {
            return orderAddressId;
        }

        public void setOrderAddressId(int orderAddressId) {
            this.orderAddressId = orderAddressId;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getAddressShort() {
            return addressShort;
        }

        public void setAddressShort(String addressShort) {
            this.addressShort = addressShort;
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

        public String getRoomNum() {
            return roomNum;
        }

        public void setRoomNum(String roomNum) {
            this.roomNum = roomNum;
        }

        public int getIsElevator() {
            return isElevator;
        }

        public void setIsElevator(int isElevator) {
            this.isElevator = isElevator;
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

        public int getFiveGasFee() {
            return fiveGasFee;
        }

        public void setFiveGasFee(int fiveGasFee) {
            this.fiveGasFee = fiveGasFee;
        }

        public int getFifteenGasFee() {
            return fifteenGasFee;
        }

        public void setFifteenGasFee(int fifteenGasFee) {
            this.fifteenGasFee = fifteenGasFee;
        }

        public int getFiftyGasFee() {
            return fiftyGasFee;
        }

        public void setFiftyGasFee(int fiftyGasFee) {
            this.fiftyGasFee = fiftyGasFee;
        }

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

        public int getFiveDepositFee() {
            return fiveDepositFee;
        }

        public void setFiveDepositFee(int fiveDepositFee) {
            this.fiveDepositFee = fiveDepositFee;
        }

        public int getFifteenDepositFee() {
            return fifteenDepositFee;
        }

        public void setFifteenDepositFee(int fifteenDepositFee) {
            this.fifteenDepositFee = fifteenDepositFee;
        }

        public int getFiftyDepositFee() {
            return fiftyDepositFee;
        }

        public void setFiftyDepositFee(int fiftyDepositFee) {
            this.fiftyDepositFee = fiftyDepositFee;
        }

        public int getRetrieveAmount() {
            return retrieveAmount;
        }

        public void setRetrieveAmount(int retrieveAmount) {
            this.retrieveAmount = retrieveAmount;
        }

        public double getRealAmount() {
            return realAmount;
        }

        public void setRealAmount(double realAmount) {
            this.realAmount = realAmount;
        }
    }
}
