package com.example;

import java.io.Serializable;
import java.sql.Date;

/**
 * Created by amarendra on 14/04/17.
 */
public class WebLog implements Serializable{

    private String city;
    private String osFamily;
    private Float latitude;
    private Float longitude;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getOsFamily() {
        return osFamily;
    }

    public void setOsFamily(String osFamily) {
        this.osFamily = osFamily;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "WebLog{" +
                "city='" + city + '\'' +
                ", osFamily='" + osFamily + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
