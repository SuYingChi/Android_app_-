package com.msht.mshtLpg.mshtLpgMaster.Bean;

import java.util.List;

public class OrdersListBeanV2 {

    /**
     * result : success
     * msg : 订单信息查询成功
     * data : {"page":{"pageNum":1,"pageSize":2,"pages":6,"total":11},"list":[{"siteId":1,"orderId":269,"orderType":1,"orderStatus":2,"orderSource":1,"isDelivery":0,"appointmentTime":"","createDate":"","remarks":"","buyer":"张三2","sex":1,"mobile":"18681497590","orderAddressId":284,"longitude":"110.30204","latitude":"20.020434","addressShort":"","address":"海南省海口市龙华区金贸西路8号诚田国际商务大厦","floor":24,"roomNum":"1","isElevator":1,"fiveBottleCount":3,"fifteenBottleCount":2,"fiftyBottleCount":2,"fiveGasFee":"","fifteenGasFee":"","fiftyGasFee":"","fiveDeliveryFee":"","fifteenDeliveryFee":"","fiftyDeliveryFee":"","fiveDepositFee":"","fifteenDepositFee":"","fiftyDepositFee":"","reFiveBottleCount":0,"reFifteenBottleCount":0,"reFiftyBottleCount":0,"retrieveAmount":"","realAmount":145.7,"depositList":[]},{"siteId":1,"orderId":234,"orderType":1,"orderStatus":2,"orderSource":1,"isDelivery":0,"appointmentTime":"","createDate":"","remarks":"","buyer":"李四","sex":1,"mobile":"18976991792","orderAddressId":249,"longitude":"201.21","latitude":"111.54","addressShort":"","address":"海口市长滨路32号111","floor":15,"roomNum":"","isElevator":0,"fiveBottleCount":1,"fifteenBottleCount":0,"fiftyBottleCount":0,"fiveGasFee":"","fifteenGasFee":"","fiftyGasFee":"","fiveDeliveryFee":"","fifteenDeliveryFee":"","fiftyDeliveryFee":"","fiveDepositFee":"","fifteenDepositFee":"","fiftyDepositFee":"","reFiveBottleCount":0,"reFifteenBottleCount":0,"reFiftyBottleCount":0,"retrieveAmount":"","realAmount":45,"depositList":[]}]}
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
         * page : {"pageNum":1,"pageSize":2,"pages":6,"total":11}
         * list : [{"siteId":1,"orderId":269,"orderType":1,"orderStatus":2,"orderSource":1,"isDelivery":0,"appointmentTime":"","createDate":"","remarks":"","buyer":"张三2","sex":1,"mobile":"18681497590","orderAddressId":284,"longitude":"110.30204","latitude":"20.020434","addressShort":"","address":"海南省海口市龙华区金贸西路8号诚田国际商务大厦","floor":24,"roomNum":"1","isElevator":1,"fiveBottleCount":3,"fifteenBottleCount":2,"fiftyBottleCount":2,"fiveGasFee":"","fifteenGasFee":"","fiftyGasFee":"","fiveDeliveryFee":"","fifteenDeliveryFee":"","fiftyDeliveryFee":"","fiveDepositFee":"","fifteenDepositFee":"","fiftyDepositFee":"","reFiveBottleCount":0,"reFifteenBottleCount":0,"reFiftyBottleCount":0,"retrieveAmount":"","realAmount":145.7,"depositList":[]},{"siteId":1,"orderId":234,"orderType":1,"orderStatus":2,"orderSource":1,"isDelivery":0,"appointmentTime":"","createDate":"","remarks":"","buyer":"李四","sex":1,"mobile":"18976991792","orderAddressId":249,"longitude":"201.21","latitude":"111.54","addressShort":"","address":"海口市长滨路32号111","floor":15,"roomNum":"","isElevator":0,"fiveBottleCount":1,"fifteenBottleCount":0,"fiftyBottleCount":0,"fiveGasFee":"","fifteenGasFee":"","fiftyGasFee":"","fiveDeliveryFee":"","fifteenDeliveryFee":"","fiftyDeliveryFee":"","fiveDepositFee":"","fifteenDepositFee":"","fiftyDepositFee":"","reFiveBottleCount":0,"reFifteenBottleCount":0,"reFiftyBottleCount":0,"retrieveAmount":"","realAmount":45,"depositList":[]}]
         */

        private PageBean page;
        private List<ListBean> list;

        public PageBean getPage() {
            return page;
        }

        public void setPage(PageBean page) {
            this.page = page;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class PageBean {
            /**
             * pageNum : 1
             * pageSize : 2
             * pages : 6
             * total : 11
             */

            private int pageNum;
            private int pageSize;
            private int pages;
            private int total;

            public int getPageNum() {
                return pageNum;
            }

            public void setPageNum(int pageNum) {
                this.pageNum = pageNum;
            }

            public int getPageSize() {
                return pageSize;
            }

            public void setPageSize(int pageSize) {
                this.pageSize = pageSize;
            }

            public int getPages() {
                return pages;
            }

            public void setPages(int pages) {
                this.pages = pages;
            }

            public int getTotal() {
                return total;
            }

            public void setTotal(int total) {
                this.total = total;
            }
        }

        public static class ListBean {
            /**
             * siteId : 1
             * orderId : 269
             * orderType : 1
             * orderStatus : 2
             * orderSource : 1
             * isDelivery : 0
             * appointmentTime :
             * createDate :
             * remarks :
             * buyer : 张三2
             * sex : 1
             * mobile : 18681497590
             * orderAddressId : 284
             * longitude : 110.30204
             * latitude : 20.020434
             * addressShort :
             * address : 海南省海口市龙华区金贸西路8号诚田国际商务大厦
             * floor : 24
             * roomNum : 1
             * isElevator : 1
             * fiveBottleCount : 3
             * fifteenBottleCount : 2
             * fiftyBottleCount : 2
             * fiveGasFee :
             * fifteenGasFee :
             * fiftyGasFee :
             * fiveDeliveryFee :
             * fifteenDeliveryFee :
             * fiftyDeliveryFee :
             * fiveDepositFee :
             * fifteenDepositFee :
             * fiftyDepositFee :
             * reFiveBottleCount : 0
             * reFifteenBottleCount : 0
             * reFiftyBottleCount : 0
             * retrieveAmount :
             * realAmount : 145.7
             * depositList : []
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
            private String fiveGasFee;
            private String fifteenGasFee;
            private String fiftyGasFee;
            private String fiveDeliveryFee;
            private String fifteenDeliveryFee;
            private String fiftyDeliveryFee;
            private String fiveDepositFee;
            private String fifteenDepositFee;
            private String fiftyDepositFee;
            private int reFiveBottleCount;
            private int reFifteenBottleCount;
            private int reFiftyBottleCount;
            private String retrieveAmount;
            private double realAmount;
            private List<?> depositList;

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

            public String getFiveGasFee() {
                return fiveGasFee;
            }

            public void setFiveGasFee(String fiveGasFee) {
                this.fiveGasFee = fiveGasFee;
            }

            public String getFifteenGasFee() {
                return fifteenGasFee;
            }

            public void setFifteenGasFee(String fifteenGasFee) {
                this.fifteenGasFee = fifteenGasFee;
            }

            public String getFiftyGasFee() {
                return fiftyGasFee;
            }

            public void setFiftyGasFee(String fiftyGasFee) {
                this.fiftyGasFee = fiftyGasFee;
            }

            public String getFiveDeliveryFee() {
                return fiveDeliveryFee;
            }

            public void setFiveDeliveryFee(String fiveDeliveryFee) {
                this.fiveDeliveryFee = fiveDeliveryFee;
            }

            public String getFifteenDeliveryFee() {
                return fifteenDeliveryFee;
            }

            public void setFifteenDeliveryFee(String fifteenDeliveryFee) {
                this.fifteenDeliveryFee = fifteenDeliveryFee;
            }

            public String getFiftyDeliveryFee() {
                return fiftyDeliveryFee;
            }

            public void setFiftyDeliveryFee(String fiftyDeliveryFee) {
                this.fiftyDeliveryFee = fiftyDeliveryFee;
            }

            public String getFiveDepositFee() {
                return fiveDepositFee;
            }

            public void setFiveDepositFee(String fiveDepositFee) {
                this.fiveDepositFee = fiveDepositFee;
            }

            public String getFifteenDepositFee() {
                return fifteenDepositFee;
            }

            public void setFifteenDepositFee(String fifteenDepositFee) {
                this.fifteenDepositFee = fifteenDepositFee;
            }

            public String getFiftyDepositFee() {
                return fiftyDepositFee;
            }

            public void setFiftyDepositFee(String fiftyDepositFee) {
                this.fiftyDepositFee = fiftyDepositFee;
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

            public String getRetrieveAmount() {
                return retrieveAmount;
            }

            public void setRetrieveAmount(String retrieveAmount) {
                this.retrieveAmount = retrieveAmount;
            }

            public double getRealAmount() {
                return realAmount;
            }

            public void setRealAmount(double realAmount) {
                this.realAmount = realAmount;
            }

            public List<?> getDepositList() {
                return depositList;
            }

            public void setDepositList(List<?> depositList) {
                this.depositList = depositList;
            }
        }
    }
}
