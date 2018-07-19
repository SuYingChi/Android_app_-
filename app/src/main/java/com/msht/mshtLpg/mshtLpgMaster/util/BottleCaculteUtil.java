package com.msht.mshtLpg.mshtLpgMaster.util;

import com.msht.mshtLpg.mshtLpgMaster.Bean.VerifyBottleBean;

import java.util.List;

public class BottleCaculteUtil {

    private static int remian5;
    private static int remian15;
    private static int remian50;

    public static   int getBottleNum(List<VerifyBottleBean> list, int weight){
        int num = 0;
        for(VerifyBottleBean bean:list){
            if (bean.getData().getBottleWeight() == weight){
                num++;
            }
        }
        return num;
    }

    public static void setRemian5(int remian5) {
        BottleCaculteUtil.remian5 = remian5;
    }

    public static void setRemian15(int remian15) {
        BottleCaculteUtil.remian15 = remian15;
    }

    public static void setRemian50(int remian50) {
        BottleCaculteUtil.remian50 = remian50;
    }
  /*  public static int getExchangeBottleFare(List<BottleReplacePriceBean.DataBean> list,int year,int weight,int level){
        for(BottleReplacePriceBean.DataBean dataBean:list){

        }
    }*/
}
