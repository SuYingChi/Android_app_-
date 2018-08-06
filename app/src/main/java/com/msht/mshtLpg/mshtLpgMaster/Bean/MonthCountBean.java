package com.msht.mshtLpg.mshtLpgMaster.Bean;


import java.util.List;

public class MonthCountBean {


    private double deliveryFee;
    private String year;
    private String month;

    public MonthCountBean(double deliveryFee, String year, String month) {
        this.deliveryFee = deliveryFee;
        this.year = year;
        this.month = month;
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
