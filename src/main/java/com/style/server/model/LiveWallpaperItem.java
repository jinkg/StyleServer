package com.style.server.model;

/**
 * jinyalin
 * on 2017/7/28.
 */
public class LiveWallpaperItem {
    public String wallpaperId;
    public String link;
    public String name;
    public String author;
    public String iconUrl;
    public String downloadUrl;
    public String providerName;
    public int minVersion;

    public boolean lazyDownload = false;
    public boolean needAd = false;

    public String checkSum;

    // v2.0 new
    public long size;
}
