package com.msht.mshtLpg.mshtLpgMaster.Bean;

import java.io.Serializable;
import java.util.List;

public class ExchangeRclBean implements Serializable{

    private int mSelectBottleModeIndex;

    public int getmSelectBottleLevelIndex() {
        return mSelectBottleLevelIndex;
    }

    public void setSelectBottleLevelIndex(int mSelectBottleLevelIndex) {
        this.mSelectBottleLevelIndex = mSelectBottleLevelIndex;
    }

    private int mSelectBottleLevelIndex;
    private List<String> mBottleModelList;
    private List<String> mBottleYearsList;

    public List<String> getmBottleLevelList() {
        return mBottleLevelList;
    }

    public void setmBottleLevelList(List<String> mBottleLevelList) {
        this.mBottleLevelList = mBottleLevelList;
    }

    private List<String> mBottleLevelList;
    private int mSelectBottleYearsIndex;
    private int bottleNum;

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    private double discount;


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
