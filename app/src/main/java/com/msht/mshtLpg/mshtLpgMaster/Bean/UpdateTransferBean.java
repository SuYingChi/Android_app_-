package com.msht.mshtLpg.mshtLpgMaster.Bean;

public class UpdateTransferBean {

    /**
     * result : success
     * msg : 调拨单修改成功
     * data : {"id":26,"ids":"","transformType":1,"dept":"海师站","siteName":"","planDay":1532620800000,"proposer":"test","siteId":1,"fiveCount":20,"fifteenCount":10,"fifthCount":10,"trackName":"","state":0,"bottleIds":""}
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
         * id : 26
         * ids :
         * transformType : 1
         * dept : 海师站
         * siteName :
         * planDay : 1532620800000
         * proposer : test
         * siteId : 1
         * fiveCount : 20
         * fifteenCount : 10
         * fifthCount : 10
         * trackName :
         * state : 0
         * bottleIds :
         */

        private int id;
        private String ids;
        private int transformType;
        private String dept;
        private String siteName;
        private long planDay;
        private String proposer;
        private int siteId;
        private int fiveCount;
        private int fifteenCount;
        private int fifthCount;
        private String trackName;
        private int state;
        private String bottleIds;

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

        public int getTransformType() {
            return transformType;
        }

        public void setTransformType(int transformType) {
            this.transformType = transformType;
        }

        public String getDept() {
            return dept;
        }

        public void setDept(String dept) {
            this.dept = dept;
        }

        public String getSiteName() {
            return siteName;
        }

        public void setSiteName(String siteName) {
            this.siteName = siteName;
        }

        public long getPlanDay() {
            return planDay;
        }

        public void setPlanDay(long planDay) {
            this.planDay = planDay;
        }

        public String getProposer() {
            return proposer;
        }

        public void setProposer(String proposer) {
            this.proposer = proposer;
        }

        public int getSiteId() {
            return siteId;
        }

        public void setSiteId(int siteId) {
            this.siteId = siteId;
        }

        public int getFiveCount() {
            return fiveCount;
        }

        public void setFiveCount(int fiveCount) {
            this.fiveCount = fiveCount;
        }

        public int getFifteenCount() {
            return fifteenCount;
        }

        public void setFifteenCount(int fifteenCount) {
            this.fifteenCount = fifteenCount;
        }

        public int getFifthCount() {
            return fifthCount;
        }

        public void setFifthCount(int fifthCount) {
            this.fifthCount = fifthCount;
        }

        public String getTrackName() {
            return trackName;
        }

        public void setTrackName(String trackName) {
            this.trackName = trackName;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public String getBottleIds() {
            return bottleIds;
        }

        public void setBottleIds(String bottleIds) {
            this.bottleIds = bottleIds;
        }
    }
}
