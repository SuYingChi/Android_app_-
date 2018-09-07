package com.msht.mshtlpgmaster.Bean;

import java.util.List;

public class TransferStorageListBean {

    /**
     * result : success
     * msg : 查询成功
     * data : {"page":{"pageNum":1,"pageSize":0,"pages":0,"total":0},"list":[{"id":1,"ids":"","transformType":1,"dept":"龙华门市部","siteName":"秀英气站","planDay":1543075200000,"proposer":"小年轻","siteId":2,"fiveCount":15,"fifteenCount":20,"fifthCount":50,"trackName":"--","state":1,"bottleIds":""},{"id":5,"ids":"","transformType":1,"dept":"","siteName":"","planDay":1530374400000,"proposer":"","siteId":2,"fiveCount":1,"fifteenCount":1,"fifthCount":1,"trackName":"--","state":0,"bottleIds":""}]}
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
         * page : {"pageNum":1,"pageSize":0,"pages":0,"total":0}
         * list : [{"id":1,"ids":"","transformType":1,"dept":"龙华门市部","siteName":"秀英气站","planDay":1543075200000,"proposer":"小年轻","siteId":2,"fiveCount":15,"fifteenCount":20,"fifthCount":50,"trackName":"--","state":1,"bottleIds":""},{"id":5,"ids":"","transformType":1,"dept":"","siteName":"","planDay":1530374400000,"proposer":"","siteId":2,"fiveCount":1,"fifteenCount":1,"fifthCount":1,"trackName":"--","state":0,"bottleIds":""}]
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
             * pageSize : 0
             * pages : 0
             * total : 0
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
             * id : 1
             * ids :
             * transformType : 1
             * dept : 龙华门市部
             * siteName : 秀英气站
             * planDay : 1543075200000
             * proposer : 小年轻
             * siteId : 2
             * fiveCount : 15
             * fifteenCount : 20
             * fifthCount : 50
             * trackName : --
             * state : 1
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
}
