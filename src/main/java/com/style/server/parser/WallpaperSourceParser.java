package com.style.server.parser;

import com.style.server.ChecksumUtil;
import com.style.server.model.WallpaperItem;

import java.io.File;
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
    private static final int FIELD_COUNT = 5;

    private static final long CACHE_VALID_TIMEOUT = 2 * 60 * 60 * 1000L;

    private static final List<WallpaperItem> mCachedWallpaper = new ArrayList<>();
    private static long lastRefreshCacheTime;

    private static boolean maybeInvalidCache() {
        long currentTime = System.currentTimeMillis();
        return currentTime - lastRefreshCacheTime > CACHE_VALID_TIMEOUT;
    }

    public static synchronized List<WallpaperItem> parseToList() {
        if (!maybeInvalidCache()) {
            return mCachedWallpaper;
        }

        List<WallpaperItem> wallpaperItems = new ArrayList<>();
        ArrayList<String[]> itemFileds = FileParser.parseFile(WALLPAPER_SOURCE_FILE);
        for (String[] fields : itemFileds) {
            WallpaperItem item = parseFields(fields);
            if (item != null) {
                wallpaperItems.add(item);
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
        if (wallpaperFields != null && wallpaperFields.length == FIELD_COUNT) {

            WallpaperItem item = new WallpaperItem(UUID.randomUUID().toString(), wallpaperFields[0].trim(),
                    wallpaperFields[1].trim(), wallpaperFields[2].trim(),
                    wallpaperFields[4].trim());

            item.pro = Integer.parseInt(wallpaperFields[3].trim()) == 1;
            String filePath = WALLPAPER_SOURCE_DIR + item.fileName;
            item.size = new File(filePath).length();
            item.checksum = ChecksumUtil.getChecksum(filePath);
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
            String filePath = WALLPAPER_DEMO_DIR + item.fileName;
            item.size = new File(filePath).length();
            item.checksum = ChecksumUtil.getChecksum(filePath);
            if (item.checksum == null || item.checksum.length() == 0) {
                wallpaperItems.remove(item);
            }
        }

        return wallpaperItems;
    }
}
