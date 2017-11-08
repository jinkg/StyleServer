package com.style.server.model;

/**
 * jinyalin
 * on 2017/11/8.
 */
public class VideoWallpaperItem {
    public String wallpaperId;
    public String name;
    public String iconUri;
    public String videoUri;

    public String author;

    public String byline;
    public String checksum;

    public long size;
    public boolean pro;

    @Override
    public String toString() {
        return name + " " + checksum;
    }
}
