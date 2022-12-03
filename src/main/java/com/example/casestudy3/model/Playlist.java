package com.example.casestudy3.model;

import java.time.LocalDate;
import java.sql.Date;

public class Playlist {
    private long id;
    private String name;
    private String avatar;
    private Date date;
    private long userId;

    public Playlist() {
    }

    public Playlist(long id, String name, String avatar, Date date, long userId) {
        this.id = id;
        this.name = name;
        this.avatar = avatar;
        this.date = date;
        this.userId = userId;
    }

    public Playlist(String name, String avatar, Date date, long userId) {
        this.name = name;
        this.avatar = avatar;
        this.date = date;
        this.userId = userId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
