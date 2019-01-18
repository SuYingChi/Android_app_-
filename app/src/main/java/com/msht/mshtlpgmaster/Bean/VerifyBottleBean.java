package com.msht.mshtlpgmaster.Bean;

import java.io.Serializable;

public class VerifyBottleBean implements Serializable {

    /**
     * result : success
     * msg : 钢瓶验证成功
     * data : {"id":24994,"ids":"","isScrap":1,"isHeavy":1,"siteId":-1,"bottleCode":"8880024994","bottleNum":"","bottleWeight":15,"producer":"浙江民泰钢瓶厂","propertyUnit":"海南民生液化气有限公司","createTime":"2018-08","checkTime":"","lastCheckTime":"","nextCheckTime":"2022-08","checkStatus":0,"discardTime":"","status":3,"stationId":21,"trackId":"","employeeId":"","employeeName":"","userId":"","orderId":"","deliveryBottleIds":"","recycleBottleIds":"","builder":"系统","bottleDownloadUrl":"http://lpg-code.oss-cn-shenzhen.aliyuncs.com/barcode/8880024994_label.png","useDays":"","fillsite":"","filltime":"","fillweight":"","actualFillWeight":"","flowDetail":"","scanner":"","startDate":"","endDate":""}
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
         * id : 24994
         * ids :
         * isScrap : 1
         * isHeavy : 1
         * siteId : -1
         * bottleCode : 8880024994
         * bottleNum :
         * bottleWeight : 15
         * producer : 浙江民泰钢瓶厂
         * propertyUnit : 海南民生液化气有限公司
         * createTime : 2018-08
         * checkTime :
         * lastCheckTime :
         * nextCheckTime : 2022-08
         * checkStatus : 0
         * discardTime :
         * status : 3
         * stationId : 21
         * trackId :
         * employeeId :
         * employeeName :
         * userId :
         * orderId :
         * deliveryBottleIds :
         * recycleBottleIds :
         * builder : 系统
         * bottleDownloadUrl : http://lpg-code.oss-cn-shenzhen.aliyuncs.com/barcode/8880024994_label.png
         * useDays :
         * fillsite :
         * filltime :
         * fillweight :
         * actualFillWeight :
         * flowDetail :
         * scanner :
         * startDate :
         * endDate :
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
        private int stationId;
        private String trackId;
        private String employeeId;
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
        private String actualFillWeight;
        private String flowDetail;
        private String scanner;
        private String startDate;
        private String endDate;

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

        public int getStationId() {
            return stationId;
        }

        public void setStationId(int stationId) {
            this.stationId = stationId;
        }

        public String getTrackId() {
            return trackId;
        }

        public void setTrackId(String trackId) {
            this.trackId = trackId;
        }

        public String getEmployeeId() {
            return employeeId;
        }

        public void setEmployeeId(String employeeId) {
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

        public String getActualFillWeight() {
            return actualFillWeight;
        }

        public void setActualFillWeight(String actualFillWeight) {
            this.actualFillWeight = actualFillWeight;
        }

        public String getFlowDetail() {
            return flowDetail;
        }

        public void setFlowDetail(String flowDetail) {
            this.flowDetail = flowDetail;
        }

        public String getScanner() {
            return scanner;
        }

        public void setScanner(String scanner) {
            this.scanner = scanner;
        }

        public String getStartDate() {
            return startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }
    }
}
