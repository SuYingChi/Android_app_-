package com.msht.mshtLpg.mshtLpgMaster.Bean;

import java.util.List;

public class ExchangeRclBean {

    private int mSelectBottleModeIndex;
    private List<String> mBottleModelList;
    private List<String> mBottleYearsList;
    private int mSelectBottleYearsIndex;
    private int bottleNum;

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    private int discount;


    public int getBottleNum() {
        return bottleNum;
    }

    public void setBottleNum(int bottleNum) {
        this.bottleNum = bottleNum;
    }




    public int getSelectBottleModeIndex() {
        return mSelectBottleModeIndex;
    }
    public int getSelectBottleYearsIndex() {
        return mSelectBottleYearsIndex;
    }
    public void setmSelectBottleModeIndex(int mSelectBottleModeIndex) {
        this.mSelectBottleModeIndex = mSelectBottleModeIndex;
    }

    public List<String> getmBottleModelList() {
        return mBottleModelList;
    }
    public List<String> getmBottleYearsList() {
        return mBottleYearsList;
    }
    public void setmBottleModelList(List<String> mBottleModelList) {
        this.mBottleModelList = mBottleModelList;
    }

    public void setmBottleYearsList(List<String> mBottleYearsList) {
        this.mBottleYearsList = mBottleYearsList;
    }

    public void setmSelectBottleYearsIndex(int mSelectBottleYearsIndex) {
        this.mSelectBottleYearsIndex = mSelectBottleYearsIndex;
    }
}
