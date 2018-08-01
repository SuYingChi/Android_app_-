package com.msht.mshtLpg.mshtLpgMaster.Bean;

import java.util.Collection;
import java.util.List;

public class MyBottleListBean {
    private List<ListBean> listBean;

    public List<ListBean> getListBean() {
        return listBean;
    }

    public class ListBean {

        private DataBean data;

        public DataBean getData() {
            return data;
        }
        public class DataBean {

            private String bottleWeight;
            private String bottleCode;
            private int isHeavy;

            public String getBottleWeight() {
                return bottleWeight;
            }

            public String getBottleCode() {
                return bottleCode;
            }

            public int getIsHeavy() {
                return isHeavy;
            }
        }
    }
}
