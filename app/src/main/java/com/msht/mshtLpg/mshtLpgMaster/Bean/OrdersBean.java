package com.msht.mshtLpg.mshtLpgMaster.Bean;

import java.util.List;

public class OrdersBean {

    /**
     * result : success
     * msg : 订单信息查询成功
     * data : {"page":{"pageNum":1,"pageSize":3,"pages":1,"total":3},"list":[{"isDelivery":1,"createData":"","mobile":"","buyer":"","sex":"","orderId":56,"orderType":1,"orderStatus":5,"orderSource":2,"orderAddressId":85,"longitude":"131","latitude":"12","addressShort":"","address":"金花市场","floor":3,"roomNum":"311","isElevator":1,"fiveBottleCount":1,"fifteenBottleCount":0,"fiftyBottleCount":0,"realAmount":5.2}]}
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
         * page : {"pageNum":1,"pageSize":3,"pages":1,"total":3}
         * list : [{"isDelivery":1,"createData":"","mobile":"","buyer":"","sex":"","orderId":56,"orderType":1,"orderStatus":5,"orderSource":2,"orderAddressId":85,"longitude":"131","latitude":"12","addressShort":"","address":"金花市场","floor":3,"roomNum":"311","isElevator":1,"fiveBottleCount":1,"fifteenBottleCount":0,"fiftyBottleCount":0,"realAmount":5.2}]
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
             * pageSize : 3
             * pages : 1
             * total : 3
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
             * isDelivery : 1
             * createData :
             * mobile :
             * buyer :
             * sex :
             * orderId : 56
             * orderType : 1
             * orderStatus : 5
             * orderSource : 2
             * orderAddressId : 85
             * longitude : 131
             * latitude : 12
             * addressShort :
             * address : 金花市场
             * floor : 3
             * roomNum : 311
             * isElevator : 1
             * fiveBottleCount : 1
             * fifteenBottleCount : 0
             * fiftyBottleCount : 0
             * realAmount : 5.2
             */

            private int isDelivery;
            private String createData;
            private String mobile;
            private String buyer;
            private String sex;
            private int orderId;
            private int orderType;
            private int orderStatus;
            private int orderSource;
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
            private double realAmount;

            public int getIsDelivery() {
                return isDelivery;
            }

            public void setIsDelivery(int isDelivery) {
                this.isDelivery = isDelivery;
            }

            public String getCreateData() {
                return createData;
            }

            public void setCreateData(String createData) {
                this.createData = createData;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
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

            public double getRealAmount() {
                return realAmount;
            }

            public void setRealAmount(double realAmount) {
                this.realAmount = realAmount;
            }
        }
    }
}
