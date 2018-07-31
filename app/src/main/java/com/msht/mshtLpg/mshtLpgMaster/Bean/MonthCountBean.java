package com.msht.mshtLpg.mshtLpgMaster.Bean;


import java.util.List;

public class MonthCountBean {

    private List<ListBean> listBean;

    public List<ListBean> getListBean() {
        return listBean;
    }

    public class ListBean{

        private String month;
        private String income;

        public String getMonth() {
            return month;
        }

        public String getIncome() {
            return income;
        }
    }
}
