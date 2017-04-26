package com.example;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by amarendra on 14/04/17.
 */
public class WebLogMapper {

    public static WebLog mapWebLog(JdbcStream.SqlRow sqlRow){

        WebLog webLog = new WebLog();
        String city = sqlRow.getString("city");
        String osFamily = sqlRow.getString("os_family");
        Float latitude = sqlRow.getFloat("latitude");
        Float longitude = sqlRow.getFloat("longitude");

        webLog.setCity(city);
        webLog.setOsFamily(osFamily);
        webLog.setLatitude(latitude);
        webLog.setLongitude(longitude);

        return webLog;

    }
}
