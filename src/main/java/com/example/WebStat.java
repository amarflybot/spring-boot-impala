package com.example;

import java.io.Serializable;
import java.sql.Date;

/**
 * Created by amarendra on 14/04/17.
 */
public class WebStat implements Serializable{

    private String host;
    private String domain;
    private String feature;
    private Date date;
    private Integer core;
    private Integer db;
    private Integer activeVisitor;

    public String getHost() {
        return host;
    }

    public WebStat setHost(String host) {
        this.host = host;
        return this;
    }

    public String getDomain() {
        return domain;
    }

    public WebStat setDomain(String domain) {
        this.domain = domain;
        return this;
    }

    public String getFeature() {
        return feature;
    }

    public WebStat setFeature(String feature) {
        this.feature = feature;
        return this;
    }

    public Date getDate() {
        return date;
    }

    public WebStat setDate(Date date) {
        this.date = date;
        return this;
    }

    public Integer getCore() {
        return core;
    }

    public WebStat setCore(Integer core) {
        this.core = core;
        return this;
    }

    public Integer getDb() {
        return db;
    }

    public WebStat setDb(Integer db) {
        this.db = db;
        return this;
    }

    public Integer getActiveVisitor() {
        return activeVisitor;
    }

    public WebStat setActiveVisitor(Integer activeVisitor) {
        this.activeVisitor = activeVisitor;
        return this;
    }

    @Override
    public String toString() {
        return "WebStat{" +
                "host='" + host + '\'' +
                ", domain='" + domain + '\'' +
                ", feature='" + feature + '\'' +
                ", date=" + date +
                ", core=" + core +
                ", db=" + db +
                ", activeVisitor=" + activeVisitor +
                '}';
    }
}
