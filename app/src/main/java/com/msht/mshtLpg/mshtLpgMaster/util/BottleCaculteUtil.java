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

  /*  public static int getExchangeBottleFare(List<BottleReplacePriceBean.DataBean> list,int year,int weight,int level){
        for(BottleReplacePriceBean.DataBean dataBean:list){

        }
    }*/
}
