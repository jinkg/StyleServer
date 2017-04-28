package com.style.server.parser;

import com.style.server.ChecksumUtil;
import com.style.server.model.WallpaperItem;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * jinyalin
 * on 2017/4/25.
 */
public class WallpaperSourceParser {
    private static final String WALLPAPER_SOURCE_FILE = "./wallpaper.txt";
    private static final String WALLPAPER_SOURCE_DIR = "./wallpapers/";
    private static final String WALLPAPER_DEMO_DIR = "./wallpapers/demo/";
    private static final String FILED_SEPARATOR = "\\|";
    private static final int FILED_COUNT = 4;

    private static final long CACHE_VALID_TIMEOUT = 2 * 60 * 60 * 1000L;

    private static final List<WallpaperItem> mCachedWallpaper = new ArrayList<>();
    private static long lastRefreshCacheTime;

    private static boolean maybeInvalidCache() {
        long currentTime = System.currentTimeMillis();
        return currentTime - lastRefreshCacheTime > CACHE_VALID_TIMEOUT;
    }

    public static List<WallpaperItem> parseToList() {
        if (!maybeInvalidCache()) {
            return mCachedWallpaper;
        }

        List<WallpaperItem> wallpaperItems = new ArrayList<>();
        File file = new File(WALLPAPER_SOURCE_FILE);
        BufferedReader bufferedReader = null;
        try {
            FileInputStream is = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(is);
            bufferedReader = new BufferedReader(isr);
            // comment line
            bufferedReader.readLine();
            for (String line = bufferedReader.readLine();
                 line != null && line.length() > 0;
                 line = bufferedReader.readLine()) {
                String[] wallpaperFields = line.split(FILED_SEPARATOR);
                WallpaperItem item = parseFields(wallpaperFields);
                if (item != null) {
                    wallpaperItems.add(item);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (Exception ignored) {
            }
        }
        if (wallpaperItems.size() == 0) {
            wallpaperItems = getDemoWallpapers();
        }
        mCachedWallpaper.clear();
        mCachedWallpaper.addAll(wallpaperItems);
        lastRefreshCacheTime = System.currentTimeMillis();
        return wallpaperItems;
    }

    private static WallpaperItem parseFields(String[] wallpaperFields) {
        if (wallpaperFields != null && wallpaperFields.length == FILED_COUNT) {

            WallpaperItem item = new WallpaperItem(UUID.randomUUID().toString(), wallpaperFields[0].trim(),
                    wallpaperFields[1].trim(), wallpaperFields[2].trim(),
                    wallpaperFields[3].trim());

            item.checksum = ChecksumUtil.getChecksum(WALLPAPER_SOURCE_DIR + item.fileName);
            if (item.checksum != null && item.checksum.length() > 0) {
                return item;
            }
        }
        return null;
    }

    private static List<WallpaperItem> getDemoWallpapers() {
        WallpaperItem[] itemArray = new WallpaperItem[]{
                new WallpaperItem(UUID.randomUUID().toString(),
                        "series-i-no-3.jpg",
                        "Series I, No. 3", "Georgia O'Keeffe, 1918", "kinglloy.com"),
                new WallpaperItem(UUID.randomUUID().toString(),
                        "blue-02.jpg",
                        "Blue-02", "Georgia O'Keeffe, 1916", "kinglloy.com"),
                new WallpaperItem(UUID.randomUUID().toString(),
                        "blue-morning-glories.jpg",
                        "Blue Morning Glories", "Georgia O'Keeffe, 1935", "kinglloy.com"),
                new WallpaperItem(UUID.randomUUID().toString(),
                        "bleeding-heart.jpg",
                        "Bleeding Heart", "Georgia O'Keeffe, 1932", "kinglloy.com")
        };
        List<WallpaperItem> wallpaperItems = Arrays.asList(itemArray);
        for (WallpaperItem item : wallpaperItems) {
            item.checksum = ChecksumUtil.getChecksum(WALLPAPER_DEMO_DIR + item.fileName);
            if (item.checksum == null || item.checksum.length() == 0) {
                wallpaperItems.remove(item);
            }
        }

        return wallpaperItems;
    }
}
