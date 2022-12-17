package com.example.musicplayer.model;

import android.net.Uri;

import java.io.Serializable;

public class Music implements Serializable {

    private long id;
    private String mMusicName;
    private String mSinger;
    private String mAlbum;
    private Uri mAlbumArt;
    private String mDuration;

    public String getMusicName() {
        return mMusicName;
    }

    public void setMusicName(String musicName) {
        mMusicName = musicName;
    }

    public Uri getAlbumArt() {
        return mAlbumArt;
    }

    public void setAlbumArt(Uri albumArt) {
        mAlbumArt = albumArt;
    }

    public String getSinger() {
        return mSinger;
    }

    public void setSinger(String singer) {
        mSinger = singer;
    }

    public String getAlbum() {
        return mAlbum;
    }

    public void setAlbum(String album) {
        mAlbum = album;
    }

    public int getDuration() {
        return Integer.parseInt(mDuration);
    }

    public void setDuration(String duration) {
        mDuration = duration;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
