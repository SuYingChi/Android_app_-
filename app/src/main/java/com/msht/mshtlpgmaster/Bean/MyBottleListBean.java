package com.msht.mshtlpgmaster.Bean;

import java.util.List;

public class MyBottleListBean {

    /**
     * result : success
     * msg : 查询成功
     * data : {"list":[{"id":7,"ids":"","isScrap":1,"isHeavy":0,"siteId":"","bottleCode":"8880000007","bottleNum":"","bottleWeight":5,"producer":"浙江民泰钢瓶厂","propertyUnit":"海南民生液化气有限公司","createTime":"2018-08","checkTime":"","lastCheckTime":"","nextCheckTime":"2022-08","checkStatus":0,"discardTime":"","status":4,"stationId":14,"trackId":"","employeeId":91,"employeeName":"","userId":"","orderId":"","deliveryBottleIds":"","recycleBottleIds":"","builder":"系统","bottleDownloadUrl":"","useDays":"","fillsite":"","filltime":"","fillweight":"","startDate":"","endDate":""},{"id":16,"ids":"","isScrap":1,"isHeavy":0,"siteId":"","bottleCode":"8880000016","bottleNum":"","bottleWeight":15,"producer":"浙江民泰钢瓶厂","propertyUnit":"海南民生液化气有限公司","createTime":"2018-08","checkTime":"","lastCheckTime":"","nextCheckTime":"2022-08","checkStatus":0,"discardTime":"","status":4,"stationId":14,"trackId":"","employeeId":91,"employeeName":"","userId":"","orderId":"","deliveryBottleIds":"","recycleBottleIds":"","builder":"系统","bottleDownloadUrl":"","useDays":"","fillsite":"","filltime":"","fillweight":"","startDate":"","endDate":""}]}
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
             * id : 7
             * ids :
             * isScrap : 1
             * isHeavy : 0
             * siteId :
             * bottleCode : 8880000007
             * bottleNum :
             * bottleWeight : 5
             * producer : 浙江民泰钢瓶厂
             * propertyUnit : 海南民生液化气有限公司
             * createTime : 2018-08
             * checkTime :
             * lastCheckTime :
             * nextCheckTime : 2022-08
             * checkStatus : 0
             * discardTime :
             * status : 4
             * stationId : 14
             * trackId :
             * employeeId : 91
             * employeeName :
             * userId :
             * orderId :
             * deliveryBottleIds :
             * recycleBottleIds :
             * builder : 系统
             * bottleDownloadUrl :
             * useDays :
             * fillsite :
             * filltime :
             * fillweight :
             * startDate :
             * endDate :
             */

            private int isHeavy;
            private String bottleCode;
            private int bottleWeight;

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

            public int getBottleWeight() {
                return bottleWeight;
            }

            public void setBottleWeight(int bottleWeight) {
                this.bottleWeight = bottleWeight;
            }
        }
    }
}
