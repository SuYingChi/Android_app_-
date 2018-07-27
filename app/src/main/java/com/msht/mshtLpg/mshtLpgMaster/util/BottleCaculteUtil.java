package com.msht.mshtLpg.mshtLpgMaster.util;

import com.msht.mshtLpg.mshtLpgMaster.Bean.OrderDetailBean;
import com.msht.mshtLpg.mshtLpgMaster.Bean.VerifyBottleBean;

import java.util.List;

public class BottleCaculteUtil {

    private static int remian5;
    private static int remian15;
    private static int remian50;

    public static int getBottleNum(List<VerifyBottleBean> list, int weight) {
        int num = 0;
        for (VerifyBottleBean bean : list) {
            if (bean.getData().getBottleWeight() == weight) {
                num++;
            }
        }
        return num;
    }

    public static boolean isContainBottle(List<VerifyBottleBean> list, String bottleCode) {
        for (VerifyBottleBean bean : list) {
            if (bean.getData().getBottleCode().equals(bottleCode)) {
                return true;
            }
        }
        return false;
    }

    /*  public static int getExchangeBottleFare(List<BottleReplacePriceBean.DataBean> list,int year,int weight,int level){
          for(BottleReplacePriceBean.DataBean dataBean:list){

          }
      }*/
    public static String getDeposite(OrderDetailBean bean, int weight) {

        List<OrderDetailBean.DataBean.DepositListBean> list = bean.getData().getDepositList();
        double deposite = 0;


        for (OrderDetailBean.DataBean.DepositListBean depositListBean : list) {
            if (depositListBean.getBottleWeight() == weight) {
                deposite += depositListBean.getReFiveAmount();
            }
        }

        return deposite + "";
    }
}
