package com.example.casestudy3.model;

public class PlaylistDetail {
    private long playListId;
    private long songId;

    public PlaylistDetail() {
    }

    public PlaylistDetail(long playListId, long songId) {
        this.playListId = playListId;
        this.songId = songId;
    }

    public long getPlayListId() {
        return playListId;
    }

    public void setPlayListId(long playListId) {
        this.playListId = playListId;
    }

    public long getSongId() {
        return songId;
    }

    public void setSongId(long songId) {
        this.songId = songId;
    }
}
