package com.mikedupuis.weeurl.entity;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;

@Entity
@Table(name = "WEE_URL")
public class WeeUrl {
    @GeneratedValue
    @Id
    @Column(name="SHORT_URL_ID")
    private int id;

    @Column(name="FULL_URL")
    private String fullUrl;

    @Column(name="SHORT_URL")
    private String shortUrl;

    @Column(name="USED_COUNT")
    private BigInteger usedCount;

    @Column(name="CREATED_TIME")
    private Date created;

    @Column(name="LAST_USED_TIME")
    private Date lastUsed;

    private static final BigInteger ONE_USAGE = BigInteger.valueOf(1);

    public void use() {
        setLastUsed(new Date());
        setUsedCount(usedCount.add(ONE_USAGE));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullUrl() {
        return fullUrl;
    }

    public void setFullUrl(String fullUrl) {
        this.fullUrl = fullUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public BigInteger getUsedCount() {
        return usedCount;
    }

    public void setUsedCount(BigInteger usedCount) {
        this.usedCount = usedCount;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getLastUsed() {
        return lastUsed;
    }

    public void setLastUsed(Date lastUsed) {
        this.lastUsed = lastUsed;
    }
}
