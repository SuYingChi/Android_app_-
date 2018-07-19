package com.msht.mshtLpg.mshtLpgMaster.Bean;

import java.util.List;

public class OrdersListBeanV2 {

    /**
     * result : success
     * msg : 订单信息查询成功
     * data : {"page":{"pageNum":1,"pageSize":10,"pages":2,"total":12},"list":[{"siteId":"","orderId":158,"orderType":1,"orderStatus":2,"orderSource":1,"isDelivery":0,"appointmentTime":"","createDate":"","remarks":"","buyer":"黄水亮","sex":1,"mobile":"13098966725","orderAddressId":"","longitude":"","latitude":"","addressShort":"","address":"","floor":"","roomNum":"","isElevator":"","fiveBottleCount":0,"fifteenBottleCount":1,"fiftyBottleCount":0,"fiveGasFee":"","fifteenGasFee":"","fiftyGasFee":"","fiveDeliveryFee":"","fifteenDeliveryFee":"","fiftyDeliveryFee":"","fiveDepositFee":"","fifteenDepositFee":"","fiftyDepositFee":"","retrieveAmount":"","realAmount":15.2,"bottleReplaceList":""},{"siteId":"","orderId":156,"orderType":1,"orderStatus":2,"orderSource":1,"isDelivery":0,"appointmentTime":"","createDate":"","remarks":"","buyer":"陈宏","sex":1,"mobile":"18976475828","orderAddressId":171,"longitude":"110.302035","latitude":"20.020395","addressShort":"","address":"龙华区秀英街道金茂西路诚田商务大厦A栋24楼","floor":24,"roomNum":"001","isElevator":1,"fiveBottleCount":2,"fifteenBottleCount":1,"fiftyBottleCount":1,"fiveGasFee":"","fifteenGasFee":"","fiftyGasFee":"","fiveDeliveryFee":"","fifteenDeliveryFee":"","fiftyDeliveryFee":"","fiveDepositFee":"","fifteenDepositFee":"","fiftyDepositFee":"","retrieveAmount":"","realAmount":96.5,"bottleReplaceList":""},{"siteId":"","orderId":155,"orderType":1,"orderStatus":2,"orderSource":1,"isDelivery":0,"appointmentTime":"","createDate":"","remarks":"","buyer":"陈宏","sex":1,"mobile":"18976475828","orderAddressId":170,"longitude":"110.302035","latitude":"20.020395","addressShort":"","address":"龙华区秀英街道金茂西路诚田商务大厦A栋24楼","floor":24,"roomNum":"001","isElevator":1,"fiveBottleCount":1,"fifteenBottleCount":0,"fiftyBottleCount":0,"fiveGasFee":"","fifteenGasFee":"","fiftyGasFee":"","fiveDeliveryFee":"","fifteenDeliveryFee":"","fiftyDeliveryFee":"","fiveDepositFee":"","fifteenDepositFee":"","fiftyDepositFee":"","retrieveAmount":"","realAmount":25.9,"bottleReplaceList":""},{"siteId":"","orderId":146,"orderType":1,"orderStatus":2,"orderSource":1,"isDelivery":0,"appointmentTime":"","createDate":"","remarks":"","buyer":"陈宏","sex":1,"mobile":"18976475828","orderAddressId":161,"longitude":"110.302035","latitude":"20.020395","addressShort":"","address":"龙华区秀英街道金茂西路诚田商务大厦A栋24楼","floor":24,"roomNum":"001","isElevator":1,"fiveBottleCount":0,"fifteenBottleCount":2,"fiftyBottleCount":9,"fiveGasFee":"","fifteenGasFee":"","fiftyGasFee":"","fiveDeliveryFee":"","fifteenDeliveryFee":"","fiftyDeliveryFee":"","fiveDepositFee":"","fifteenDepositFee":"","fiftyDepositFee":"","retrieveAmount":"","realAmount":482.2,"bottleReplaceList":""},{"siteId":"","orderId":74,"orderType":1,"orderStatus":5,"orderSource":1,"isDelivery":0,"appointmentTime":"","createDate":"","remarks":"","buyer":"李四","sex":1,"mobile":"18976991792","orderAddressId":103,"longitude":"56.56","latitude":"23.90","addressShort":"","address":"海口市白龙北路5号","floor":5,"roomNum":"","isElevator":0,"fiveBottleCount":2,"fifteenBottleCount":0,"fiftyBottleCount":0,"fiveGasFee":"","fifteenGasFee":"","fiftyGasFee":"","fiveDeliveryFee":"","fifteenDeliveryFee":"","fiftyDeliveryFee":"","fiveDepositFee":"","fifteenDepositFee":"","fiftyDepositFee":"","retrieveAmount":"","realAmount":45,"bottleReplaceList":""},{"siteId":"","orderId":73,"orderType":1,"orderStatus":1,"orderSource":1,"isDelivery":0,"appointmentTime":"","createDate":"","remarks":"","buyer":"李四","sex":1,"mobile":"18976991792","orderAddressId":102,"longitude":"56.56","latitude":"23.90","addressShort":"","address":"海口市白龙北路5555555555号","floor":1,"roomNum":"","isElevator":0,"fiveBottleCount":1,"fifteenBottleCount":0,"fiftyBottleCount":0,"fiveGasFee":"","fifteenGasFee":"","fiftyGasFee":"","fiveDeliveryFee":"","fifteenDeliveryFee":"","fiftyDeliveryFee":"","fiveDepositFee":"","fifteenDepositFee":"","fiftyDepositFee":"","retrieveAmount":"","realAmount":45,"bottleReplaceList":""},{"siteId":"","orderId":56,"orderType":1,"orderStatus":5,"orderSource":2,"isDelivery":1,"appointmentTime":"","createDate":"","remarks":"","buyer":"刘备","sex":1,"mobile":"18779884643","orderAddressId":85,"longitude":"131","latitude":"12","addressShort":"","address":"金花市场","floor":3,"roomNum":"311","isElevator":1,"fiveBottleCount":1,"fifteenBottleCount":0,"fiftyBottleCount":0,"fiveGasFee":"","fifteenGasFee":"","fiftyGasFee":"","fiveDeliveryFee":"","fifteenDeliveryFee":"","fiftyDeliveryFee":"","fiveDepositFee":"","fifteenDepositFee":"","fiftyDepositFee":"","retrieveAmount":"","realAmount":5.2,"bottleReplaceList":""},{"siteId":"","orderId":54,"orderType":1,"orderStatus":2,"orderSource":2,"isDelivery":1,"appointmentTime":"","createDate":"","remarks":"","buyer":"刘备","sex":1,"mobile":"18779884643","orderAddressId":83,"longitude":"131","latitude":"12","addressShort":"","address":"金花市场","floor":3,"roomNum":"311","isElevator":1,"fiveBottleCount":0,"fifteenBottleCount":0,"fiftyBottleCount":1,"fiveGasFee":"","fifteenGasFee":"","fiftyGasFee":"","fiveDeliveryFee":"","fifteenDeliveryFee":"","fiftyDeliveryFee":"","fiveDepositFee":"","fifteenDepositFee":"","fiftyDepositFee":"","retrieveAmount":"","realAmount":50.2,"bottleReplaceList":""},{"siteId":"","orderId":50,"orderType":1,"orderStatus":2,"orderSource":2,"isDelivery":1,"appointmentTime":"","createDate":"","remarks":"","buyer":"刘备","sex":1,"mobile":"18779884643","orderAddressId":78,"longitude":"131","latitude":"12","addressShort":"","address":"金花市场","floor":3,"roomNum":"311","isElevator":1,"fiveBottleCount":0,"fifteenBottleCount":0,"fiftyBottleCount":1,"fiveGasFee":"","fifteenGasFee":"","fiftyGasFee":"","fiveDeliveryFee":"","fifteenDeliveryFee":"","fiftyDeliveryFee":"","fiveDepositFee":"","fifteenDepositFee":"","fiftyDepositFee":"","retrieveAmount":"","realAmount":50.2,"bottleReplaceList":""},{"siteId":"","orderId":49,"orderType":1,"orderStatus":2,"orderSource":2,"isDelivery":1,"appointmentTime":"","createDate":"","remarks":"","buyer":"张三","sex":1,"mobile":"18976991791","orderAddressId":77,"longitude":"56.56","latitude":"23.90","addressShort":"","address":"海口市白龙北路5号","floor":3,"roomNum":"111","isElevator":1,"fiveBottleCount":1,"fifteenBottleCount":0,"fiftyBottleCount":0,"fiveGasFee":"","fifteenGasFee":"","fiftyGasFee":"","fiveDeliveryFee":"","fifteenDeliveryFee":"","fiftyDeliveryFee":"","fiveDepositFee":"","fifteenDepositFee":"","fiftyDepositFee":"","retrieveAmount":"","realAmount":5.2,"bottleReplaceList":""}]}
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
         * page : {"pageNum":1,"pageSize":10,"pages":2,"total":12}
         * list : [{"siteId":"","orderId":158,"orderType":1,"orderStatus":2,"orderSource":1,"isDelivery":0,"appointmentTime":"","createDate":"","remarks":"","buyer":"黄水亮","sex":1,"mobile":"13098966725","orderAddressId":"","longitude":"","latitude":"","addressShort":"","address":"","floor":"","roomNum":"","isElevator":"","fiveBottleCount":0,"fifteenBottleCount":1,"fiftyBottleCount":0,"fiveGasFee":"","fifteenGasFee":"","fiftyGasFee":"","fiveDeliveryFee":"","fifteenDeliveryFee":"","fiftyDeliveryFee":"","fiveDepositFee":"","fifteenDepositFee":"","fiftyDepositFee":"","retrieveAmount":"","realAmount":15.2,"bottleReplaceList":""},{"siteId":"","orderId":156,"orderType":1,"orderStatus":2,"orderSource":1,"isDelivery":0,"appointmentTime":"","createDate":"","remarks":"","buyer":"陈宏","sex":1,"mobile":"18976475828","orderAddressId":171,"longitude":"110.302035","latitude":"20.020395","addressShort":"","address":"龙华区秀英街道金茂西路诚田商务大厦A栋24楼","floor":24,"roomNum":"001","isElevator":1,"fiveBottleCount":2,"fifteenBottleCount":1,"fiftyBottleCount":1,"fiveGasFee":"","fifteenGasFee":"","fiftyGasFee":"","fiveDeliveryFee":"","fifteenDeliveryFee":"","fiftyDeliveryFee":"","fiveDepositFee":"","fifteenDepositFee":"","fiftyDepositFee":"","retrieveAmount":"","realAmount":96.5,"bottleReplaceList":""},{"siteId":"","orderId":155,"orderType":1,"orderStatus":2,"orderSource":1,"isDelivery":0,"appointmentTime":"","createDate":"","remarks":"","buyer":"陈宏","sex":1,"mobile":"18976475828","orderAddressId":170,"longitude":"110.302035","latitude":"20.020395","addressShort":"","address":"龙华区秀英街道金茂西路诚田商务大厦A栋24楼","floor":24,"roomNum":"001","isElevator":1,"fiveBottleCount":1,"fifteenBottleCount":0,"fiftyBottleCount":0,"fiveGasFee":"","fifteenGasFee":"","fiftyGasFee":"","fiveDeliveryFee":"","fifteenDeliveryFee":"","fiftyDeliveryFee":"","fiveDepositFee":"","fifteenDepositFee":"","fiftyDepositFee":"","retrieveAmount":"","realAmount":25.9,"bottleReplaceList":""},{"siteId":"","orderId":146,"orderType":1,"orderStatus":2,"orderSource":1,"isDelivery":0,"appointmentTime":"","createDate":"","remarks":"","buyer":"陈宏","sex":1,"mobile":"18976475828","orderAddressId":161,"longitude":"110.302035","latitude":"20.020395","addressShort":"","address":"龙华区秀英街道金茂西路诚田商务大厦A栋24楼","floor":24,"roomNum":"001","isElevator":1,"fiveBottleCount":0,"fifteenBottleCount":2,"fiftyBottleCount":9,"fiveGasFee":"","fifteenGasFee":"","fiftyGasFee":"","fiveDeliveryFee":"","fifteenDeliveryFee":"","fiftyDeliveryFee":"","fiveDepositFee":"","fifteenDepositFee":"","fiftyDepositFee":"","retrieveAmount":"","realAmount":482.2,"bottleReplaceList":""},{"siteId":"","orderId":74,"orderType":1,"orderStatus":5,"orderSource":1,"isDelivery":0,"appointmentTime":"","createDate":"","remarks":"","buyer":"李四","sex":1,"mobile":"18976991792","orderAddressId":103,"longitude":"56.56","latitude":"23.90","addressShort":"","address":"海口市白龙北路5号","floor":5,"roomNum":"","isElevator":0,"fiveBottleCount":2,"fifteenBottleCount":0,"fiftyBottleCount":0,"fiveGasFee":"","fifteenGasFee":"","fiftyGasFee":"","fiveDeliveryFee":"","fifteenDeliveryFee":"","fiftyDeliveryFee":"","fiveDepositFee":"","fifteenDepositFee":"","fiftyDepositFee":"","retrieveAmount":"","realAmount":45,"bottleReplaceList":""},{"siteId":"","orderId":73,"orderType":1,"orderStatus":1,"orderSource":1,"isDelivery":0,"appointmentTime":"","createDate":"","remarks":"","buyer":"李四","sex":1,"mobile":"18976991792","orderAddressId":102,"longitude":"56.56","latitude":"23.90","addressShort":"","address":"海口市白龙北路5555555555号","floor":1,"roomNum":"","isElevator":0,"fiveBottleCount":1,"fifteenBottleCount":0,"fiftyBottleCount":0,"fiveGasFee":"","fifteenGasFee":"","fiftyGasFee":"","fiveDeliveryFee":"","fifteenDeliveryFee":"","fiftyDeliveryFee":"","fiveDepositFee":"","fifteenDepositFee":"","fiftyDepositFee":"","retrieveAmount":"","realAmount":45,"bottleReplaceList":""},{"siteId":"","orderId":56,"orderType":1,"orderStatus":5,"orderSource":2,"isDelivery":1,"appointmentTime":"","createDate":"","remarks":"","buyer":"刘备","sex":1,"mobile":"18779884643","orderAddressId":85,"longitude":"131","latitude":"12","addressShort":"","address":"金花市场","floor":3,"roomNum":"311","isElevator":1,"fiveBottleCount":1,"fifteenBottleCount":0,"fiftyBottleCount":0,"fiveGasFee":"","fifteenGasFee":"","fiftyGasFee":"","fiveDeliveryFee":"","fifteenDeliveryFee":"","fiftyDeliveryFee":"","fiveDepositFee":"","fifteenDepositFee":"","fiftyDepositFee":"","retrieveAmount":"","realAmount":5.2,"bottleReplaceList":""},{"siteId":"","orderId":54,"orderType":1,"orderStatus":2,"orderSource":2,"isDelivery":1,"appointmentTime":"","createDate":"","remarks":"","buyer":"刘备","sex":1,"mobile":"18779884643","orderAddressId":83,"longitude":"131","latitude":"12","addressShort":"","address":"金花市场","floor":3,"roomNum":"311","isElevator":1,"fiveBottleCount":0,"fifteenBottleCount":0,"fiftyBottleCount":1,"fiveGasFee":"","fifteenGasFee":"","fiftyGasFee":"","fiveDeliveryFee":"","fifteenDeliveryFee":"","fiftyDeliveryFee":"","fiveDepositFee":"","fifteenDepositFee":"","fiftyDepositFee":"","retrieveAmount":"","realAmount":50.2,"bottleReplaceList":""},{"siteId":"","orderId":50,"orderType":1,"orderStatus":2,"orderSource":2,"isDelivery":1,"appointmentTime":"","createDate":"","remarks":"","buyer":"刘备","sex":1,"mobile":"18779884643","orderAddressId":78,"longitude":"131","latitude":"12","addressShort":"","address":"金花市场","floor":3,"roomNum":"311","isElevator":1,"fiveBottleCount":0,"fifteenBottleCount":0,"fiftyBottleCount":1,"fiveGasFee":"","fifteenGasFee":"","fiftyGasFee":"","fiveDeliveryFee":"","fifteenDeliveryFee":"","fiftyDeliveryFee":"","fiveDepositFee":"","fifteenDepositFee":"","fiftyDepositFee":"","retrieveAmount":"","realAmount":50.2,"bottleReplaceList":""},{"siteId":"","orderId":49,"orderType":1,"orderStatus":2,"orderSource":2,"isDelivery":1,"appointmentTime":"","createDate":"","remarks":"","buyer":"张三","sex":1,"mobile":"18976991791","orderAddressId":77,"longitude":"56.56","latitude":"23.90","addressShort":"","address":"海口市白龙北路5号","floor":3,"roomNum":"111","isElevator":1,"fiveBottleCount":1,"fifteenBottleCount":0,"fiftyBottleCount":0,"fiveGasFee":"","fifteenGasFee":"","fiftyGasFee":"","fiveDeliveryFee":"","fifteenDeliveryFee":"","fiftyDeliveryFee":"","fiveDepositFee":"","fifteenDepositFee":"","fiftyDepositFee":"","retrieveAmount":"","realAmount":5.2,"bottleReplaceList":""}]
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
             * pageSize : 10
             * pages : 2
             * total : 12
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
             * siteId :
             * orderId : 158
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
             * orderAddressId :
             * longitude :
             * latitude :
             * addressShort :
             * address :
             * floor :
             * roomNum :
             * isElevator :
             * fiveBottleCount : 0
             * fifteenBottleCount : 1
             * fiftyBottleCount : 0
             * fiveGasFee :
             * fifteenGasFee :
             * fiftyGasFee :
             * fiveDeliveryFee :
             * fifteenDeliveryFee :
             * fiftyDeliveryFee :
             * fiveDepositFee :
             * fifteenDepositFee :
             * fiftyDepositFee :
             * retrieveAmount :
             * realAmount : 15.2
             * bottleReplaceList :
             */

            private String siteId;
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
            private String orderAddressId;
            private String longitude;
            private String latitude;
            private String addressShort;
            private String address;
            private String floor;
            private String roomNum;
            private String isElevator;
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
            private String retrieveAmount;
            private double realAmount;
            private String bottleReplaceList;

            public String getSiteId() {
                return siteId;
            }

            public void setSiteId(String siteId) {
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

            public String getOrderAddressId() {
                return orderAddressId;
            }

            public void setOrderAddressId(String orderAddressId) {
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

            public String getFloor() {
                return floor;
            }

            public void setFloor(String floor) {
                this.floor = floor;
            }

            public String getRoomNum() {
                return roomNum;
            }

            public void setRoomNum(String roomNum) {
                this.roomNum = roomNum;
            }

            public String getIsElevator() {
                return isElevator;
            }

            public void setIsElevator(String isElevator) {
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

            public String getBottleReplaceList() {
                return bottleReplaceList;
            }

            public void setBottleReplaceList(String bottleReplaceList) {
                this.bottleReplaceList = bottleReplaceList;
            }
        }
    }
}
