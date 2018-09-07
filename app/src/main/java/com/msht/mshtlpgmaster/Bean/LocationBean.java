package com.msht.mshtlpgmaster.Bean;

import java.io.Serializable;

/**
 * Created by hong on 2017/3/6.
 */

public class LocationBean implements Serializable {
    private double lon;
    private double lat;
    private String title;
    private String content;
    private String mCity;
    private String mArea;

    public LocationBean(double lon,double lat,String title,String content,String city,String area){
        this.lon = lon;
        this.lat = lat;
        this.title = title;
        this.content = content;
        this.mCity=city;
        this.mArea=area;
    }

    public double getLon() {
        return lon;
    }

    public double getLat() {
        return lat;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
    public String getCity() {
        return mCity;
    }
    public String getArea() {
        return mArea;
    }
}
