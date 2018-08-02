package com.msht.mshtLpg.mshtLpgMaster.Bean;

import java.util.List;

public class MyBottleListBean {

    /**
     * result : success
     * msg : 查询成功
     * data : {"list":[{"id":64,"ids":"","isScrap":1,"isHeavy":1,"siteId":2,"bottleCode":"88888906","bottleNum":"64","bottleWeight":15,"producer":"钢瓶厂家","propertyUnit":"","createTime":"2018-07-18","checkTime":"","lastCheckTime":"","nextCheckTime":"","checkStatus":0,"discardTime":"","status":4,"stationId":"","trackId":"","employeeId":55,"employeeName":"","userId":"","orderId":"","deliveryBottleIds":"","recycleBottleIds":"","builder":"客服系统-超级管理员","bottleDownloadUrl":"","useDays":"","fillsite":"","filltime":"","fillweight":""},{"id":65,"ids":"","isScrap":1,"isHeavy":0,"siteId":2,"bottleCode":"88888907","bottleNum":"65","bottleWeight":15,"producer":"钢瓶厂家","propertyUnit":"","createTime":"2018-07-18","checkTime":"","lastCheckTime":"","nextCheckTime":"","checkStatus":0,"discardTime":"","status":4,"stationId":"","trackId":"","employeeId":55,"employeeName":"","userId":"","orderId":"","deliveryBottleIds":"","recycleBottleIds":"","builder":"客服系统-超级管理员","bottleDownloadUrl":"http://lpg-code.oss-cn-shenzhen.aliyuncs.com/barcode/88888907_label.png","useDays":"","fillsite":"","filltime":"","fillweight":""}]}
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
        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 64
             * ids :
             * isScrap : 1
             * isHeavy : 1
             * siteId : 2
             * bottleCode : 88888906
             * bottleNum : 64
             * bottleWeight : 15
             * producer : 钢瓶厂家
             * propertyUnit :
             * createTime : 2018-07-18
             * checkTime :
             * lastCheckTime :
             * nextCheckTime :
             * checkStatus : 0
             * discardTime :
             * status : 4
             * stationId :
             * trackId :
             * employeeId : 55
             * employeeName :
             * userId :
             * orderId :
             * deliveryBottleIds :
             * recycleBottleIds :
             * builder : 客服系统-超级管理员
             * bottleDownloadUrl :
             * useDays :
             * fillsite :
             * filltime :
             * fillweight :
             */

            private int id;
            private String ids;
            private int isScrap;
            private int isHeavy;
            private int siteId;
            private String bottleCode;
            private String bottleNum;
            private int bottleWeight;
            private String producer;
            private String propertyUnit;
            private String createTime;
            private String checkTime;
            private String lastCheckTime;
            private String nextCheckTime;
            private int checkStatus;
            private String discardTime;
            private int status;
            private String stationId;
            private String trackId;
            private int employeeId;
            private String employeeName;
            private String userId;
            private String orderId;
            private String deliveryBottleIds;
            private String recycleBottleIds;
            private String builder;
            private String bottleDownloadUrl;
            private String useDays;
            private String fillsite;
            private String filltime;
            private String fillweight;

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

            public int getIsScrap() {
                return isScrap;
            }

            public void setIsScrap(int isScrap) {
                this.isScrap = isScrap;
            }

            public int getIsHeavy() {
                return isHeavy;
            }

            public void setIsHeavy(int isHeavy) {
                this.isHeavy = isHeavy;
            }

            public int getSiteId() {
                return siteId;
            }

            public void setSiteId(int siteId) {
                this.siteId = siteId;
            }

            public String getBottleCode() {
                return bottleCode;
            }

            public void setBottleCode(String bottleCode) {
                this.bottleCode = bottleCode;
            }

            public String getBottleNum() {
                return bottleNum;
            }

            public void setBottleNum(String bottleNum) {
                this.bottleNum = bottleNum;
            }

            public int getBottleWeight() {
                return bottleWeight;
            }

            public void setBottleWeight(int bottleWeight) {
                this.bottleWeight = bottleWeight;
            }

            public String getProducer() {
                return producer;
            }

            public void setProducer(String producer) {
                this.producer = producer;
            }

            public String getPropertyUnit() {
                return propertyUnit;
            }

            public void setPropertyUnit(String propertyUnit) {
                this.propertyUnit = propertyUnit;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getCheckTime() {
                return checkTime;
            }

            public void setCheckTime(String checkTime) {
                this.checkTime = checkTime;
            }

            public String getLastCheckTime() {
                return lastCheckTime;
            }

            public void setLastCheckTime(String lastCheckTime) {
                this.lastCheckTime = lastCheckTime;
            }

            public String getNextCheckTime() {
                return nextCheckTime;
            }

            public void setNextCheckTime(String nextCheckTime) {
                this.nextCheckTime = nextCheckTime;
            }

            public int getCheckStatus() {
                return checkStatus;
            }

            public void setCheckStatus(int checkStatus) {
                this.checkStatus = checkStatus;
            }

            public String getDiscardTime() {
                return discardTime;
            }

            public void setDiscardTime(String discardTime) {
                this.discardTime = discardTime;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getStationId() {
                return stationId;
            }

            public void setStationId(String stationId) {
                this.stationId = stationId;
            }

            public String getTrackId() {
                return trackId;
            }

            public void setTrackId(String trackId) {
                this.trackId = trackId;
            }

            public int getEmployeeId() {
                return employeeId;
            }

            public void setEmployeeId(int employeeId) {
                this.employeeId = employeeId;
            }

            public String getEmployeeName() {
                return employeeName;
            }

            public void setEmployeeName(String employeeName) {
                this.employeeName = employeeName;
            }

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public String getOrderId() {
                return orderId;
            }

            public void setOrderId(String orderId) {
                this.orderId = orderId;
            }

            public String getDeliveryBottleIds() {
                return deliveryBottleIds;
            }

            public void setDeliveryBottleIds(String deliveryBottleIds) {
                this.deliveryBottleIds = deliveryBottleIds;
            }

            public String getRecycleBottleIds() {
                return recycleBottleIds;
            }

            public void setRecycleBottleIds(String recycleBottleIds) {
                this.recycleBottleIds = recycleBottleIds;
            }

            public String getBuilder() {
                return builder;
            }

            public void setBuilder(String builder) {
                this.builder = builder;
            }

            public String getBottleDownloadUrl() {
                return bottleDownloadUrl;
            }

            public void setBottleDownloadUrl(String bottleDownloadUrl) {
                this.bottleDownloadUrl = bottleDownloadUrl;
            }

            public String getUseDays() {
                return useDays;
            }

            public void setUseDays(String useDays) {
                this.useDays = useDays;
            }

            public String getFillsite() {
                return fillsite;
            }

            public void setFillsite(String fillsite) {
                this.fillsite = fillsite;
            }

            public String getFilltime() {
                return filltime;
            }

            public void setFilltime(String filltime) {
                this.filltime = filltime;
            }

            public String getFillweight() {
                return fillweight;
            }

            public void setFillweight(String fillweight) {
                this.fillweight = fillweight;
            }
        }
    }
}
