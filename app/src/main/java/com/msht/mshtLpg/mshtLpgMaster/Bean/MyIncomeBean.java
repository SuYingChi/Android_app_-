package com.msht.mshtLpg.mshtLpgMaster.Bean;

import java.io.Serializable;
import java.util.List;

public class MyIncomeBean implements Serializable{

    /**
     * result : success
     * msg : 成功
     * data : {"totalIncome":21.7,"list":[{"orderId":204,"finishTime":"2018-08-02 09:42:30","deliveryFee":20.7,"year":"2018","month":"08"},{"orderId":205,"finishTime":"2018-08-02 09:43:03","deliveryFee":1,"year":"2018","month":"08"}]}
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
         * totalIncome : 21.7
         * list : [{"orderId":204,"finishTime":"2018-08-02 09:42:30","deliveryFee":20.7,"year":"2018","month":"08"},{"orderId":205,"finishTime":"2018-08-02 09:43:03","deliveryFee":1,"year":"2018","month":"08"}]
         */

        private double totalIncome;
        private List<ListBean> list;

        public double getTotalIncome() {
            return totalIncome;
        }

        public void setTotalIncome(double totalIncome) {
            this.totalIncome = totalIncome;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean implements Serializable{
            /**
             * orderId : 204
             * finishTime : 2018-08-02 09:42:30
             * deliveryFee : 20.7
             * year : 2018
             * month : 08
             */

            private int orderId;
            private String finishTime;
            private double deliveryFee;
            private String year;
            private String month;

            public int getOrderId() {
                return orderId;
            }

            public void setOrderId(int orderId) {
                this.orderId = orderId;
            }

            public String getFinishTime() {
                return finishTime;
            }

            public void setFinishTime(String finishTime) {
                this.finishTime = finishTime;
            }

            public double getDeliveryFee() {
                return deliveryFee;
            }

            public void setDeliveryFee(double deliveryFee) {
                this.deliveryFee = deliveryFee;
            }

            public String getYear() {
                return year;
            }

            public void setYear(String year) {
                this.year = year;
            }

            public String getMonth() {
                return month;
            }

            public void setMonth(String month) {
                this.month = month;
            }
        }
    }
}
