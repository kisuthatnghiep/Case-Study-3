package com.example.casestudy3.model;

public class Playlist {
    private long id;
    private String name;
    private String avatar;
    private long userId;

    public Playlist() {
    }

    public Playlist(long id, String name, String avatar, long userId) {
        this.id = id;
        this.name = name;
        this.avatar = avatar;
        this.userId = userId;
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
