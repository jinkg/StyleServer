package com.style.server.model;

import com.style.server.Style;

import java.io.File;

/**
 * 34921
 * 2017/1/3.
 */
public class WallpaperItem {
    public String wallpaperId;
    public String imageUri;

    public transient String fileName;
    public String title;
    public String byline;
    public String attribution;
    public String checksum;

    public WallpaperItem(String wallpaperId, String fileName, String title, String byline, String attribution) {
        this.wallpaperId = wallpaperId;
        this.fileName = fileName;
        this.title = title;
        this.byline = byline;
        this.attribution = attribution;

        this.imageUri = Style.IP + File.separator + fileName;
    }

    @Override
    public String toString() {
        return fileName + " " + checksum;
    }
}
