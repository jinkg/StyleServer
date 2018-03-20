package com.style.server.parser;

import com.style.server.ChecksumUtil;
import com.style.server.model.HDWallpaperItem;

import java.io.File;
import java.util.*;

/**
 * jinyalin
 * on 2017/4/25.
 */
public class HDWallpaperSourceParser {
    private static final String WALLPAPER_SOURCE_DIR = "./wallpapers/hd/";
    private static final String WALLPAPER_DEMO_DIR = "./wallpapers/demo/";
    private static final int FIELD_COUNT = 5;

    private static final long CACHE_VALID_TIMEOUT = 2 * 60 * 60 * 1000L;

    private static Map<String, List<HDWallpaperItem>> mCaches = new HashMap<>();
    private static Map<String, Long> mCachesRefreshTime = new HashMap<>();

    private static boolean maybeInvalidCache(String sourceFile) {
        long lastRefreshCacheTime = mCachesRefreshTime.containsKey(sourceFile) ? mCachesRefreshTime.get(sourceFile) : 0;
        long currentTime = System.currentTimeMillis();
        return currentTime - lastRefreshCacheTime > CACHE_VALID_TIMEOUT;
    }

    public static synchronized List<HDWallpaperItem> parseToList(String sourceFile) {
        List<HDWallpaperItem> cachedWallpaper = mCaches.get(sourceFile);
        if (cachedWallpaper == null) {
            cachedWallpaper = new ArrayList<>();
        }
        if (!maybeInvalidCache(sourceFile)) {
            return cachedWallpaper;
        }

        List<HDWallpaperItem> hdWallpaperItems = new ArrayList<>();
        ArrayList<String[]> itemFields = FileParser.parseFile(sourceFile);
        for (String[] fields : itemFields) {
            HDWallpaperItem item = parseFields(fields);
            if (item != null) {
                hdWallpaperItems.add(item);
            }
        }
        if (hdWallpaperItems.size() == 0) {
            hdWallpaperItems = getDemoWallpapers();
        }
        cachedWallpaper.clear();
        cachedWallpaper.addAll(hdWallpaperItems);
        mCaches.put(sourceFile, cachedWallpaper);
        mCachesRefreshTime.put(sourceFile, System.currentTimeMillis());
        return hdWallpaperItems;
    }

    private static HDWallpaperItem parseFields(String[] wallpaperFields) {
        if (wallpaperFields != null && wallpaperFields.length == FIELD_COUNT) {

            HDWallpaperItem item = new HDWallpaperItem(UUID.randomUUID().toString(), wallpaperFields[0].trim(),
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

    private static List<HDWallpaperItem> getDemoWallpapers() {
        HDWallpaperItem[] itemArray = new HDWallpaperItem[]{
                new HDWallpaperItem(UUID.randomUUID().toString(),
                        "series-i-no-3.jpg",
                        "Series I, No. 3", "Georgia O'Keeffe, 1918", "kinglloy.com"),
                new HDWallpaperItem(UUID.randomUUID().toString(),
                        "blue-02.jpg",
                        "Blue-02", "Georgia O'Keeffe, 1916", "kinglloy.com"),
                new HDWallpaperItem(UUID.randomUUID().toString(),
                        "blue-morning-glories.jpg",
                        "Blue Morning Glories", "Georgia O'Keeffe, 1935", "kinglloy.com"),
                new HDWallpaperItem(UUID.randomUUID().toString(),
                        "bleeding-heart.jpg",
                        "Bleeding Heart", "Georgia O'Keeffe, 1932", "kinglloy.com")
        };
        List<HDWallpaperItem> hdWallpaperItems = Arrays.asList(itemArray);
        for (HDWallpaperItem item : hdWallpaperItems) {
            String filePath = WALLPAPER_DEMO_DIR + item.fileName;
            item.size = new File(filePath).length();
            item.checksum = ChecksumUtil.getChecksum(filePath);
            if (item.checksum == null || item.checksum.length() == 0) {
                hdWallpaperItems.remove(item);
            }
        }

        return hdWallpaperItems;
    }
}
