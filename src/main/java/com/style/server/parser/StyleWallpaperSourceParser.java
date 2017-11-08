package com.style.server.parser;

import com.style.server.ChecksumUtil;
import com.style.server.model.StyleWallpaperItem;

import java.io.File;
import java.util.*;

/**
 * jinyalin
 * on 2017/4/25.
 */
public class StyleWallpaperSourceParser {
    private static final String WALLPAPER_SOURCE_FILE = "./wallpaper.txt";
    private static final String WALLPAPER_SOURCE_DIR = "./wallpapers/";
    private static final String WALLPAPER_DEMO_DIR = "./wallpapers/demo/";
    private static final int FIELD_COUNT = 5;

    private static final long CACHE_VALID_TIMEOUT = 2 * 60 * 60 * 1000L;

    private static Map<String, List<StyleWallpaperItem>> mCaches = new HashMap<>();
    private static Map<String, Long> mCachesRefreshTime = new HashMap<>();

    private static boolean maybeInvalidCache(String sourceFile) {
        long lastRefreshCacheTime = mCachesRefreshTime.containsKey(sourceFile) ? mCachesRefreshTime.get(sourceFile) : 0;
        long currentTime = System.currentTimeMillis();
        return currentTime - lastRefreshCacheTime > CACHE_VALID_TIMEOUT;
    }

    public static synchronized List<StyleWallpaperItem> parseToList() {
        return parseToList(WALLPAPER_SOURCE_FILE);
    }

    public static synchronized List<StyleWallpaperItem> parseToList(String sourceFile) {
        List<StyleWallpaperItem> cachedWallpaper = mCaches.get(sourceFile);
        if (cachedWallpaper == null) {
            cachedWallpaper = new ArrayList<>();
        }
        if (!maybeInvalidCache(sourceFile)) {
            return cachedWallpaper;
        }

        List<StyleWallpaperItem> styleWallpaperItems = new ArrayList<>();
        ArrayList<String[]> itemFields = FileParser.parseFile(sourceFile);
        for (String[] fields : itemFields) {
            StyleWallpaperItem item = parseFields(fields);
            if (item != null) {
                styleWallpaperItems.add(item);
            }
        }
        if (styleWallpaperItems.size() == 0) {
            styleWallpaperItems = getDemoWallpapers();
        }
        cachedWallpaper.clear();
        cachedWallpaper.addAll(styleWallpaperItems);
        mCaches.put(sourceFile, cachedWallpaper);
        mCachesRefreshTime.put(sourceFile, System.currentTimeMillis());
        return styleWallpaperItems;
    }

    private static StyleWallpaperItem parseFields(String[] wallpaperFields) {
        if (wallpaperFields != null && wallpaperFields.length == FIELD_COUNT) {

            StyleWallpaperItem item = new StyleWallpaperItem(UUID.randomUUID().toString(), wallpaperFields[0].trim(),
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

    private static List<StyleWallpaperItem> getDemoWallpapers() {
        StyleWallpaperItem[] itemArray = new StyleWallpaperItem[]{
                new StyleWallpaperItem(UUID.randomUUID().toString(),
                        "series-i-no-3.jpg",
                        "Series I, No. 3", "Georgia O'Keeffe, 1918", "kinglloy.com"),
                new StyleWallpaperItem(UUID.randomUUID().toString(),
                        "blue-02.jpg",
                        "Blue-02", "Georgia O'Keeffe, 1916", "kinglloy.com"),
                new StyleWallpaperItem(UUID.randomUUID().toString(),
                        "blue-morning-glories.jpg",
                        "Blue Morning Glories", "Georgia O'Keeffe, 1935", "kinglloy.com"),
                new StyleWallpaperItem(UUID.randomUUID().toString(),
                        "bleeding-heart.jpg",
                        "Bleeding Heart", "Georgia O'Keeffe, 1932", "kinglloy.com")
        };
        List<StyleWallpaperItem> styleWallpaperItems = Arrays.asList(itemArray);
        for (StyleWallpaperItem item : styleWallpaperItems) {
            String filePath = WALLPAPER_DEMO_DIR + item.fileName;
            item.size = new File(filePath).length();
            item.checksum = ChecksumUtil.getChecksum(filePath);
            if (item.checksum == null || item.checksum.length() == 0) {
                styleWallpaperItems.remove(item);
            }
        }

        return styleWallpaperItems;
    }
}
