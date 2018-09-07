package com.msht.mshtlpgmaster.Bean;

public class GetBottleInfo {

    /**
     * result : success
     * msg : 查询成功
     * data : {"id":4500,"ids":"","isScrap":1,"isHeavy":1,"bottleCode":"8880000001","bottleNum":"123455","bottleWeight":5,"producer":"浙江民泰","propertyUnit":"海南民生","createTime":"2018年7月","checkTime":"","lastCheckTime":"","nextCheckTime":"2022年8月","checkStatus":0,"discardTime":"","status":0,"stationId":"","trackId":"","siteId":"","employeeId":"","userId":"","builder":"系统","bottleDownloadUrl":""}
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
         * id : 4500
         * ids :
         * isScrap : 1
         * isHeavy : 1
         * bottleCode : 8880000001
         * bottleNum : 123455
         * bottleWeight : 5
         * producer : 浙江民泰
         * propertyUnit : 海南民生
         * createTime : 2018年7月
         * checkTime :
         * lastCheckTime :
         * nextCheckTime : 2022年8月
         * checkStatus : 0
         * discardTime :
         * status : 0
         * stationId :
         * trackId :
         * siteId :
         * employeeId :
         * userId :
         * builder : 系统
         * bottleDownloadUrl :
         */

        private int id;
        private String ids;
        private int isScrap;
        private int isHeavy;
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
        private String siteId;
        private String employeeId;
        private String userId;
        private String builder;
        private String bottleDownloadUrl;

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

        public String getSiteId() {
            return siteId;
        }

        public void setSiteId(String siteId) {
            this.siteId = siteId;
        }

        public String getEmployeeId() {
            return employeeId;
        }

        public void setEmployeeId(String employeeId) {
            this.employeeId = employeeId;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
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
    }
}
