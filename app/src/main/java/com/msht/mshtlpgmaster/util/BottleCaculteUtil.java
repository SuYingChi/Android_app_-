package com.msht.mshtlpgmaster.util;

import com.msht.mshtlpgmaster.Bean.MyBottleListBean;
import com.msht.mshtlpgmaster.Bean.OrderDetailBean;
import com.msht.mshtlpgmaster.Bean.VerifyBottleBean;

import java.util.List;

public class BottleCaculteUtil {


    public static int getBottleNum(List<VerifyBottleBean> list, int weight) {
        int num = 0;
        for (VerifyBottleBean bean : list) {
            if (bean.getData().getBottleWeight() == weight) {
                num++;
            }
        }
        return num;
    }

    public static int getMyBottleNum(List<MyBottleListBean.DataBean.ListBean> list, int weight) {
        int num = 0;
        for (MyBottleListBean.DataBean.ListBean bean : list) {
            if (Integer.valueOf(bean.getBottleWeight()) == weight) {
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
                if (weight == 5) {
                    deposite += depositListBean.getReFiveAmount();
                } else if (weight == 15) {
                    deposite += depositListBean.getReFifteenAmount();
                } else if (weight == 50) {
                    deposite += depositListBean.getReFiftyAmount();
                }
            }
        }

        return deposite + "";
    }

    public static String getBottleIds(List<VerifyBottleBean> list) {
        StringBuilder sf = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            if (i == 0) {
                sf.append(list.get(i).getData().getId());
            } else {
                sf.append(",").append(list.get(i).getData().getId());
            }
        }
        return sf.toString();
    }

    public static boolean checkBottleListbyOrderNum(List<VerifyBottleBean> list, int orderFiveNum, int orderFifteenNum, int orderFiftyNum) {
        if (BottleCaculteUtil.getBottleNum(list, 5) < orderFiveNum) {
            PopUtil.toastInBottom("5kg钢瓶未达到订单数");
            return false;
        } else if (BottleCaculteUtil.getBottleNum(list, 15) < orderFifteenNum) {
            PopUtil.toastInBottom("15kg钢瓶未达到订单数");
            return false;
        } else if (BottleCaculteUtil.getBottleNum(list, 50) < orderFiftyNum) {
            PopUtil.toastInBottom("50kg钢瓶未达到订单数");
            return false;
        } else {
            return true;
        }
    }
}
