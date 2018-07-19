package com.msht.mshtLpg.mshtLpgMaster.Bean;

import java.io.Serializable;
import java.lang.reflect.Field;

public class VerifyBottleBean implements Serializable{

    /**
     * result : success
     * msg : 钢瓶验证成功
     * data : {"id":15,"isScrap":1,"isHeavy":1,"siteId":2,"bottleCode":"9000005","bottleNum":"15","bottleWeight":5,"producer":"钢瓶生产厂家1","propertyUnit":"钢瓶生产厂家","createTime":"2018-04-27","checkStatus":0,"status":4,"userId":7}
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
         * id : 15
         * isScrap : 1
         * isHeavy : 1
         * siteId : 2
         * bottleCode : 9000005
         * bottleNum : 15
         * bottleWeight : 5
         * producer : 钢瓶生产厂家1
         * propertyUnit : 钢瓶生产厂家
         * createTime : 2018-04-27
         * checkStatus : 0
         * status : 4
         * userId : 7
         */

        private int id;
        private int isScrap;
        private int isHeavy;
        private int siteId;
        private String bottleCode;
        private String bottleNum;
        private int bottleWeight;
        private String producer;
        private String propertyUnit;
        private String createTime;
        private int checkStatus;
        private int status;
        private int userId;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public int getCheckStatus() {
            return checkStatus;
        }

        public void setCheckStatus(int checkStatus) {
            this.checkStatus = checkStatus;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof VerifyBottleBean) {
            VerifyBottleBean bean = (VerifyBottleBean) obj;
            return bean.getData().getBottleCode().equals(data.bottleCode);
        }
        return false;
    }

    //重写hashCode方法，把对象的name和age属性转为一个字符串，返回次字符串的hashCode值
    @Override
    public int hashCode() {
        String id = this.data.bottleCode ;
        return id.hashCode();

    }
}
