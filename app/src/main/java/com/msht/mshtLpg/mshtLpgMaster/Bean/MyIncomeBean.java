package com.msht.mshtLpg.mshtLpgMaster.Bean;

import java.util.Collection;
import java.util.List;

public class MyIncomeBean {
    private String total;
    private List<ListBean> listBean;

    public String getTotal() {
        return total;
    }

    public List<ListBean> getListBean() {
        return listBean;
    }

    public class ListBean{

        private String orderId;
        private String time;
        private String income;

        public String getOrderId() {
            return orderId;
        }

        public String getTime() {
            return time;
        }

        public String getIncome() {
            return income;
        }
    }
}
