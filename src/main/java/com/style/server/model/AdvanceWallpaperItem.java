package com.style.server.model;

/**
 * jinyalin
 * on 2017/7/28.
 */
public class AdvanceWallpaperItem {
    public String wallpaperId;
    public String link;
    public String name;
    public String author;
    public String iconUrl;
    public String downloadUrl;
    public String providerName;

    public boolean lazyDownload = false;
    public boolean needAd = false;

    public String checkSum;
}
