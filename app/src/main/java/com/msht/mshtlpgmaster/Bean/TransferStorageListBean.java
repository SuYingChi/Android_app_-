package com.msht.mshtlpgmaster.Bean;

import java.util.List;

public class TransferStorageListBean {

    /**
     * result : success
     * msg : 查询成功
     * data : {"page":{"pageNum":1,"pageSize":6,"pages":1,"total":6},"list":[{"id":113,"ids":"","transformType":2,"dept":"测试网点","siteName":"","planDay":1545840000000,"proposer":"13005099965","siteId":21,"stationId":14,"fiveCount":0,"fifteenCount":0,"fifthCount":0,"fiveFullCount":3,"fifteenFullCount":5,"fiftyFullCount":5,"trackName":"","state":2,"orderAmount":0,"scanner":"","bottleIds":"","heavryBottleIds":"","lightBottleIds":"","startDate":"","endDate":""},{"id":114,"ids":"","transformType":2,"dept":"测试网点","siteName":"","planDay":1545926400000,"proposer":"13005099965","siteId":21,"stationId":14,"fiveCount":1,"fifteenCount":3,"fifthCount":5,"fiveFullCount":2,"fifteenFullCount":4,"fiftyFullCount":6,"trackName":"","state":0,"orderAmount":3200,"scanner":"","bottleIds":"","heavryBottleIds":"","lightBottleIds":"","startDate":"","endDate":""},{"id":115,"ids":"","transformType":2,"dept":"测试网点","siteName":"","planDay":1546012800000,"proposer":"13005099965","siteId":21,"stationId":14,"fiveCount":5,"fifteenCount":2,"fifthCount":1,"fiveFullCount":1,"fifteenFullCount":2,"fiftyFullCount":3,"trackName":"","state":0,"orderAmount":1600,"scanner":"","bottleIds":"","heavryBottleIds":"","lightBottleIds":"","startDate":"","endDate":""},{"id":116,"ids":"","transformType":2,"dept":"测试网点","siteName":"","planDay":1545926400000,"proposer":"13005099965","siteId":21,"stationId":14,"fiveCount":0,"fifteenCount":0,"fifthCount":0,"fiveFullCount":2,"fifteenFullCount":3,"fiftyFullCount":3,"trackName":"","state":0,"orderAmount":1950,"scanner":"","bottleIds":"","heavryBottleIds":"","lightBottleIds":"","startDate":"","endDate":""},{"id":117,"ids":"","transformType":2,"dept":"测试网点","siteName":"","planDay":1546185600000,"proposer":"13005099965","siteId":21,"stationId":14,"fiveCount":1,"fifteenCount":0,"fifthCount":0,"fiveFullCount":0,"fifteenFullCount":1,"fiftyFullCount":0,"trackName":"","state":0,"orderAmount":200,"scanner":"","bottleIds":"","heavryBottleIds":"","lightBottleIds":"","startDate":"","endDate":""},{"id":118,"ids":"","transformType":2,"dept":"测试网点","siteName":"","planDay":1546444800000,"proposer":"13005099965","siteId":21,"stationId":14,"fiveCount":1,"fifteenCount":2,"fifthCount":1,"fiveFullCount":1,"fifteenFullCount":2,"fiftyFullCount":1,"trackName":"","state":0,"orderAmount":900,"scanner":"","bottleIds":"","heavryBottleIds":"","lightBottleIds":"","startDate":"","endDate":""}]}
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
         * page : {"pageNum":1,"pageSize":6,"pages":1,"total":6}
         * list : [{"id":113,"ids":"","transformType":2,"dept":"测试网点","siteName":"","planDay":1545840000000,"proposer":"13005099965","siteId":21,"stationId":14,"fiveCount":0,"fifteenCount":0,"fifthCount":0,"fiveFullCount":3,"fifteenFullCount":5,"fiftyFullCount":5,"trackName":"","state":2,"orderAmount":0,"scanner":"","bottleIds":"","heavryBottleIds":"","lightBottleIds":"","startDate":"","endDate":""},{"id":114,"ids":"","transformType":2,"dept":"测试网点","siteName":"","planDay":1545926400000,"proposer":"13005099965","siteId":21,"stationId":14,"fiveCount":1,"fifteenCount":3,"fifthCount":5,"fiveFullCount":2,"fifteenFullCount":4,"fiftyFullCount":6,"trackName":"","state":0,"orderAmount":3200,"scanner":"","bottleIds":"","heavryBottleIds":"","lightBottleIds":"","startDate":"","endDate":""},{"id":115,"ids":"","transformType":2,"dept":"测试网点","siteName":"","planDay":1546012800000,"proposer":"13005099965","siteId":21,"stationId":14,"fiveCount":5,"fifteenCount":2,"fifthCount":1,"fiveFullCount":1,"fifteenFullCount":2,"fiftyFullCount":3,"trackName":"","state":0,"orderAmount":1600,"scanner":"","bottleIds":"","heavryBottleIds":"","lightBottleIds":"","startDate":"","endDate":""},{"id":116,"ids":"","transformType":2,"dept":"测试网点","siteName":"","planDay":1545926400000,"proposer":"13005099965","siteId":21,"stationId":14,"fiveCount":0,"fifteenCount":0,"fifthCount":0,"fiveFullCount":2,"fifteenFullCount":3,"fiftyFullCount":3,"trackName":"","state":0,"orderAmount":1950,"scanner":"","bottleIds":"","heavryBottleIds":"","lightBottleIds":"","startDate":"","endDate":""},{"id":117,"ids":"","transformType":2,"dept":"测试网点","siteName":"","planDay":1546185600000,"proposer":"13005099965","siteId":21,"stationId":14,"fiveCount":1,"fifteenCount":0,"fifthCount":0,"fiveFullCount":0,"fifteenFullCount":1,"fiftyFullCount":0,"trackName":"","state":0,"orderAmount":200,"scanner":"","bottleIds":"","heavryBottleIds":"","lightBottleIds":"","startDate":"","endDate":""},{"id":118,"ids":"","transformType":2,"dept":"测试网点","siteName":"","planDay":1546444800000,"proposer":"13005099965","siteId":21,"stationId":14,"fiveCount":1,"fifteenCount":2,"fifthCount":1,"fiveFullCount":1,"fifteenFullCount":2,"fiftyFullCount":1,"trackName":"","state":0,"orderAmount":900,"scanner":"","bottleIds":"","heavryBottleIds":"","lightBottleIds":"","startDate":"","endDate":""}]
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
             * pageSize : 6
             * pages : 1
             * total : 6
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
             * id : 113
             * ids :
             * transformType : 2
             * dept : 测试网点
             * siteName :
             * planDay : 1545840000000
             * proposer : 13005099965
             * siteId : 21
             * stationId : 14
             * fiveCount : 0
             * fifteenCount : 0
             * fifthCount : 0
             * fiveFullCount : 3
             * fifteenFullCount : 5
             * fiftyFullCount : 5
             * trackName :
             * state : 2
             * orderAmount : 0
             * scanner :
             * bottleIds :
             * heavryBottleIds :
             * lightBottleIds :
             * startDate :
             * endDate :
             */

            private int id;
            private String ids;
            private int transformType;
            private String dept;
            private String siteName;
            private long planDay;
            private String proposer;
            private int siteId;
            private int stationId;
            private int fiveCount;
            private int fifteenCount;
            private int fifthCount;
            private int fiveFullCount;
            private int fifteenFullCount;
            private int fiftyFullCount;
            private String trackName;
            private int state;
            private int orderAmount;
            private String scanner;
            private String bottleIds;
            private String heavryBottleIds;
            private String lightBottleIds;
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

            public int getStationId() {
                return stationId;
            }

            public void setStationId(int stationId) {
                this.stationId = stationId;
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

            public int getFiveFullCount() {
                return fiveFullCount;
            }

            public void setFiveFullCount(int fiveFullCount) {
                this.fiveFullCount = fiveFullCount;
            }

            public int getFifteenFullCount() {
                return fifteenFullCount;
            }

            public void setFifteenFullCount(int fifteenFullCount) {
                this.fifteenFullCount = fifteenFullCount;
            }

            public int getFiftyFullCount() {
                return fiftyFullCount;
            }

            public void setFiftyFullCount(int fiftyFullCount) {
                this.fiftyFullCount = fiftyFullCount;
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

            public int getOrderAmount() {
                return orderAmount;
            }

            public void setOrderAmount(int orderAmount) {
                this.orderAmount = orderAmount;
            }

            public String getScanner() {
                return scanner;
            }

            public void setScanner(String scanner) {
                this.scanner = scanner;
            }

            public String getBottleIds() {
                return bottleIds;
            }

            public void setBottleIds(String bottleIds) {
                this.bottleIds = bottleIds;
            }

            public String getHeavryBottleIds() {
                return heavryBottleIds;
            }

            public void setHeavryBottleIds(String heavryBottleIds) {
                this.heavryBottleIds = heavryBottleIds;
            }

            public String getLightBottleIds() {
                return lightBottleIds;
            }

            public void setLightBottleIds(String lightBottleIds) {
                this.lightBottleIds = lightBottleIds;
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
}
